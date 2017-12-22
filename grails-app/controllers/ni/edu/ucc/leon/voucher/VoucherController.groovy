package ni.edu.ucc.leon.voucher

import com.craigburke.document.builder.PdfDocumentBuilder
import grails.validation.ValidationException

import ni.edu.ucc.leon.EmployeeVoucherService
import ni.edu.ucc.leon.GuestVoucherService
import ni.edu.ucc.leon.CoffeeShopService
import ni.edu.ucc.leon.LocationService
import ni.edu.ucc.leon.ActivityService
import ni.edu.ucc.leon.EmployeeService
import ni.edu.ucc.leon.VoucherService

import ni.edu.ucc.leon.CoordinationGuest
import ni.edu.ucc.leon.EmployeeVoucher
import ni.edu.ucc.leon.GuestVoucher
import ni.edu.ucc.leon.CoffeeShop
import ni.edu.ucc.leon.Employee
import ni.edu.ucc.leon.Activity
import ni.edu.ucc.leon.Voucher
import ni.edu.ucc.leon.Guest

class VoucherController {

    EmployeeVoucherService employeeVoucherService
    GuestVoucherService guestVoucherService
    CoffeeShopService coffeeShopService
    LocationService locationService
    ActivityService activityService
    EmployeeService employeeService
    VoucherService voucherService

    static allowedMethods = [ save: 'POST', store: 'POST', delete: 'DELETE' ]

    def index(final Long employeeId, final Long activityId, final String type) {
        respond ([activity: activityService.find(activityId)], model: [model: createModel(activityId)])
    }

    def save(SaveEmployeeVoucherCommand command) {
        if (command.hasErrors()) {
            respond ([errors: command.errors], model: [model: createModel(command.activityId)], view: 'index')

            return
        }

        try {
            Number result = employeeVoucherService.save(command)

            flash.message = "$result vales creados"
            redirect uri: "/employees/${command.employeeId}/activities/${command.activityId}/vouchers/${command.type}", method: 'GET'
        } catch(ValidationException e) {
            respond ([errors: e.errors], model: [model: createModel(command.activity)], view: 'index')
        }
    }

    def store(SaveGuestVoucherCommand command) {
        if (command.hasErrors()) {
            respond ([errors: command.errors], model: [model: createModel(command.activityId)], view: 'index')

            return
        }

        try {
            Number result = guestVoucherService.save(command)

            flash.message = "$result vales creados"
            redirect uri: "/employees/${command.employeeId}/activities/${command.activityId}/vouchers/${command.type}", method: 'GET'
        } catch(ValidationException e) {
            respond ([errors: e.errors], model: [model: createModel(command.activityId)], view: 'index')
        }
    }

    def show(final Long employeeId, final Long activityId, final Long id) {
        respond id ? ([voucher: voucherService.find(id)]) : null
    }

    def delete(final Long employeeId, final Long activityId, final Long id) {
        Voucher voucher = voucherService.delete(id)
        String type = voucher.instanceOf(EmployeeVoucher) ? 'employee' : 'guest'

        flash.message = 'Vale eliminado'
        redirect uri: "/employees/$employeeId/activities/$activityId/vouchers/$type", method: 'GET'
    }

    def employeeList(final Long activityId, final String date) {
        java.sql.Timestamp timestamp = Date.parse('yyyy-MM-dd', date).toTimestamp()
        List<Map> employeeList = employeeVoucherService.getEmployeeList(activityId, timestamp).collect { employee ->
            [id: employee.id, fullName: employee.fullName]
        }

        respond ([participantList: employeeList], formats: ['json'])
    }

    def guestList(final Long activityId, final String date) {
        java.sql.Timestamp timestamp = Date.parse('yyyy-MM-dd', date).toTimestamp()
        List<Map> guestList = guestVoucherService.getGuestList(activityId, timestamp).collect { guest ->
            [id: guest.id, fullName: guest.fullName]
        }

        respond ([participantList: guestList], formats: ['json'])
    }

    def print(final Long activityId) {
        PdfDocumentBuilder pdfDocumentBuilder = new PdfDocumentBuilder(response.outputStream)
        Activity activity = activityService.find(activityId)
        List<EmployeeVoucher> employeeVoucherList = EmployeeVoucher.where { activity == activity }.list()
        List<GuestVoucher> guestVoucherList = GuestVoucher.where { activity == activity }.list()
        List<Voucher> voucherList = employeeVoucherList + guestVoucherList

        pdfDocumentBuilder.create {
            document (
                margin:
                    [top: 0.1.inches, right: 0.2.inches, bottom: 0.2.inches, left: 0.2.inches],
                font:
                    [family: 'Courier', size: 9.pt]
            ) {
                voucherList.eachWithIndex { voucher, index, iterator = index + 1 ->
                    table(columns: [1, 2], padding: 1.px, margin: [bottom: 0.1.inches, top: 0.1.inches]) {
                        row {
                            cell 'A nombre de'
                            cell voucher.instanceOf(EmployeeVoucher) ? voucher.employee.fullName : voucher.guest.fullName
                        }

                        row {
                            cell 'Coordinacion'
                            cell voucher.activity.organizedBy.name
                        }

                        row {
                            cell 'Fecha'
                            cell voucher.date.format('yyyy-MM-dd')
                        }

                        row {
                            cell 'Valor'
                            cell voucher.price
                        }

                        row {
                            cell 'Servicios'
                            cell getVoucherServices(voucher)
                        }

                        row {
                            cell "Autorizado por: ${voucher.activity.authorizedBy.fullName}, fecha: ${voucher.activity.authorizationDate.format('yyyy-MM-dd hh:mm')}", colspan: 2, align: 'center'
                        }
                    }

                    if (iterator % 6 == 0) pageBreak()
                }
            }
        }

        response.contentType = 'application/pdf'
        response.setHeader('Content-disposition', 'attachment;filename=document.pdf')
        response.outputStream.flush()
    }

    private Model createModel(final Long activityId) {
        // TODO: Improve this logic
        List<EmployeeVoucher> employeeVoucherList = employeeVoucherService.findAllByActivity(activityId)
        List<GuestVoucher> guestVoucherList = guestVoucherService.findAllByActivity(activityId)

        List<Employee> employeeList = employeeVoucherList ? employeeVoucherService.getEmployeeList(activityId, employeeVoucherList.first().date) : employeeService.findAllByEnabled(true)
        List<Guest> guestList = guestVoucherList ? guestVoucherService.getGuestList(activityId, guestVoucherList.first().date) : ni.edu.ucc.leon.CoordinationGuest.guestList(activityService.find(activityId).organizedBy)

        new Model(
            guestList: guestList,
            employeeList: employeeList,
            locationDateList: locationService.locationDateList(activityId),
            coffeeShopList: coffeeShopService.findAll(),
            employeeVoucherList: employeeVoucherService.getVoucherSummary(activityId),
            guestVoucherList: guestVoucherService.getVoucherSummary(activityId)
        )
    }

    private String getVoucherServices(final Voucher voucher) {
        List<Map> summary = [
            [label: 'Desayuno', service: voucher.breakfast],
            [label: 'Almuerzo', service: voucher.lunch],
            [label: 'Cena', service: voucher.dinner],
            [label: 'Otros', service: voucher.others],
        ]

        List<String> result = summary.inject([]) { accumulator, currentValue ->
            if (currentValue.service) accumulator << currentValue.label

            accumulator
        }

        result.join(', ')
    }
}

class Model {
    List<Guest> guestList
    List<Employee> employeeList
    List<Date> locationDateList
    List<CoffeeShop> coffeeShopList
    List<EmployeeVoucher> employeeVoucherList
    List<GuestVoucher> guestVoucherList
}
