package ni.edu.ucc.leon.voucher

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
        respond ([], model: [model: createModel(activityId)])
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
}

class Model {
    List<Guest> guestList
    List<Employee> employeeList
    List<Date> locationDateList
    List<CoffeeShop> coffeeShopList
    List<EmployeeVoucher> employeeVoucherList
    List<GuestVoucher> guestVoucherList
}
