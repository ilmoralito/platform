package ni.edu.ucc.leon.voucher

import grails.plugin.springsecurity.SpringSecurityService
import com.craigburke.document.builder.PdfDocumentBuilder
import grails.validation.ValidationException

import ni.edu.ucc.leon.FixedVoucherService
import ni.edu.ucc.leon.CoffeeShopService
import ni.edu.ucc.leon.EmployeeService

import ni.edu.ucc.leon.FixedVoucher
import ni.edu.ucc.leon.CoffeeShop
import ni.edu.ucc.leon.Employee
import ni.edu.ucc.leon.User

import ni.edu.ucc.leon.voucher.Util

class FixedVoucherController {

    SpringSecurityService springSecurityService
    FixedVoucherService fixedVoucherService
    CoffeeShopService coffeeShopService
    EmployeeService employeeService

    static allowedMethods = [ save: 'POST', delete: 'DELETE', update: 'PUT']

    def index() {
        List<FixedVoucher> fixedVoucherList = fixedVoucherService.findAll()
        List<Employee> employeeList = getEmployeeListToFilter(fixedVoucherList)

        respond ([fixedVoucherList: getFixedVoucherList(fixedVoucherList)], model: [employeeList: employeeList])
    }

    def create(BuildFixedVoucher command) {
        if (command.hasErrors()) {
            List<FixedVoucher> fixedVoucherList = fixedVoucherService.findAll()
            List<Employee> employeeList = getEmployeeListToFilter(fixedVoucherList)

            respond (
                [errors: command.errors],
                model: [
                    fixedVoucherList: getFixedVoucherList(fixedVoucherList),
                    employeeList: employeeList
                ],
                view: 'index'
            )

            return
        }

        respond (
            [date: command.date],
            model: [
                coffeeShopList: coffeeShopService.findAll(),
                employeeList: getEmployeeListInDate(command.date),
                fixedVoucherList: buildFixedVoucherList(getFixedVoucherListInDate(command.date))
            ])
    }

    def save(SaveFixedVoucher command) {
        if (command.hasErrors()) {
            respond (
                [errors: command.errors],
                view: 'create',
                model:[
                    date: command.date,
                    fixedVoucherList: buildFixedVoucherList(getFixedVoucherListInDate(command.date)),
                    employeeList: getEmployeeListInDate(command.date),
                    coffeeShopList: coffeeShopService.findAll()
                ],
            )

            return
        }

        FixedVoucher voucher = new FixedVoucher(
            date: command.date,
            coffeeShop: command.coffeeShop,
            coordination: command.coordination,
            price: command.price,
            breakfast: command.breakfast,
            lunch: command.lunch,
            dinner: command.dinner,
            others: command.others,
            employee: command.employee
        )

        voucher.save(flush: true)

        flash.message = 'Vale creado'
        respond (
            [fixedVoucherList: buildFixedVoucherList(getFixedVoucherListInDate(command.date))],
            model: [
                date: command.date,
                employeeList: getEmployeeListInDate(command.date),
                coffeeShopList: coffeeShopService.findAll()
            ], view: 'create'
        )
    }

    def show(final Long id) {
        FixedVoucher fixedVoucher = id ? fixedVoucherService.find(id) : null
        List<String> serviceList = [
            [label: 'Desayuno', value: fixedVoucher.breakfast],
            [label: 'Almuerzo', value: fixedVoucher.lunch],
            [label: 'Cena', value: fixedVoucher.dinner],
            [label: 'Otros', value: fixedVoucher.others]
        ]


        respond ([fixedVoucher: fixedVoucher], model: [serviceList: getServiceList(serviceList)])
    }

    def edit(final Long id) {
        FixedVoucher fixedVoucher = fixedVoucherService.find(id)

        if (!fixedVoucher) response.sendError 404

        respond (
            [fixedVoucher: fixedVoucher],
            model: [
                employeeList: getEmployeeList(fixedVoucher),
                coffeeShopList: coffeeShopService.findAll()
            ]
        )
    }

    def update(UpdateFixedVoucher command) {
        if (command.hasErrors()) {
            FixedVoucher fixedVoucher = fixedVoucherService.find(command.id)

            respond (
                [errors: command.errors],
                model: [
                    fixedVoucher: fixedVoucher,
                    employeeList: getEmployeeList(fixedVoucher),
                    coffeeShopList: coffeeShopService.findAll(),
                ],
                view: 'edit'
            )

            return
        }

        try {
            FixedVoucher fixedVoucher = fixedVoucherService.update(command)

            flash.message = 'Vale actualizado'
            redirect fixedVoucher
        } catch(ValidationException e) {
            respond (
                [errors: e.errors],
                model: [
                    fixedVoucher: e,
                    employeeList: getEmployeeList(e),
                    coffeeShopList: coffeeShopService.findAll(),
                ],
                view: 'edit'
            )
        }
    }

    def delete(final Long id) {
        FixedVoucher fixedVoucher = FixedVoucher.get(id)

        if (!fixedVoucher) response.sendError 404

        fixedVoucher.delete(flush: true)

        flash.message = 'Vale eliminado'
        redirect uri: "/fixed/vouchers/create/${fixedVoucher.date.format('yyyy-MM-dd')}", method: 'GET'
    }

    def print(PrintFixedVouchers command) {
        if (command.hasErrors()) {
            List<FixedVoucher> fixedVoucherList = fixedVoucherService.findAll()
            List<Employee> employeeList = getEmployeeListToFilter(fixedVoucherList)

            respond (
                [errors: command.errors],
                model:
                    [
                        fixedVoucherList: getFixedVoucherList(fixedVoucherList),
                        employeeList: employeeList
                    ],
                    view: 'index'
            )

            return
        }

        List<FixedVoucher> fixedVouchers = FixedVoucher.getAll(command.fixedVouchers)
        PdfDocumentBuilder pdfDocumentBuilder = new PdfDocumentBuilder(response.outputStream)
        pdfDocumentBuilder.create {
            document (
                margin:
                    [top: 0.1.inches, right: 0.2.inches, bottom: 0.2.inches, left: 0.2.inches],
                font:
                    [family: 'Courier', size: 9.pt]
            ) {
                fixedVouchers.eachWithIndex { FixedVoucher fixedVoucher, index, iterator = index + 1 ->
                    table(
                        columns: [1, 2],
                        padding: 1.px,
                        margin: [bottom: 0.1.inches, top: 0.1.inches]
                    ) {
                        row {
                            cell 'A nombre de'
                            cell fixedVoucher.employee.fullName
                        }

                        row {
                            cell 'Coordinacion'
                            cell fixedVoucher.coordination.name
                        }

                        row {
                            cell 'Fecha'
                            cell fixedVoucher.date.format('yyyy-MM-dd')
                        }

                        row {
                            cell 'Valor'
                            cell fixedVoucher.price
                        }

                        row {
                            cell 'Servicios'
                            cell Util.getServiceList(fixedVoucher)
                        }

                        row {
                            User currentUser = springSecurityService.currentUser
                            String printedBy = currentUser.employee.fullName
                            String printingDate = new Date().format('yyyy-MM-dd')

                            cell "Impreso por: $printedBy, Fecha de impresion: $printingDate", colspan: 2, align: 'center'
                        }
                    }

                    if (iterator % 6 == 0) pageBreak()
                }
            }
        }

        response.contentType = 'application/pdf'
        response.setHeader('Content-disposition', 'attachment;filename=doc.pdf')
        response.outputStream.flush()
    }

    def filter() {
        render params
    }

    private List<Employee> getEmployeeListToFilter(final List<FixedVoucher> fixedVoucherList) {
        fixedVoucherList.employee.unique().sort { it.fullName }
    }

    private List<Map> getFixedVoucherList(final List<FixedVoucher> fixedVoucherList) {
        List<Map> results = fixedVoucherList.groupBy { it.date }.collect {
            [
                date: it.key.format('yyyy-MM-dd'),
                fixedVoucherList: it.value.collect { FixedVoucher fixedVoucher ->
                    [
                        id: fixedVoucher.id,
                        employee: [
                            id: fixedVoucher.employee.id,
                            fullName: fixedVoucher.employee.fullName
                        ],
                        price: fixedVoucher.price,
                        serviceList: getServiceList([
                            [label: 'Desayuno', value: fixedVoucher.breakfast],
                            [label: 'Almuerzo', value: fixedVoucher.lunch],
                            [label: 'Cena', value: fixedVoucher.dinner],
                            [label: 'Otros', value: fixedVoucher.others]
                        ])
                    ]
                }
            ]
        }

        results.sort { a, b -> b.date <=> a.date }
    }

    private List<Map> buildFixedVoucherList(final List<FixedVoucher> fixedVoucherList) {
        List<Map> results = fixedVoucherList.collect { FixedVoucher fixedVoucher ->
            [
                id: fixedVoucher.id,
                employee: [fullName: fixedVoucher.employee.fullName],
                price: fixedVoucher.price,
                serviceList: [
                    [label: 'Desayuno', value: fixedVoucher.breakfast],
                    [label: 'Almuerzo', value: fixedVoucher.lunch],
                    [label: 'Cena', value: fixedVoucher.dinner],
                    [label: 'Otros', value: fixedVoucher.others]
                ].inject([]) { accumulator, currentValue ->
                    if (currentValue.value) accumulator << currentValue.label

                    accumulator
                }
            ]
        }

        results
    }

    private List<Employee> getEmployeeListInDate(final Date date) {
        List<Employee> employeeList = employeeService.list()
        List<Employee> anotherEmployeeList = getFixedVoucherListInDate(date).employee

        employeeList - anotherEmployeeList
    }

    private List<Employee> getEmployeeList(final FixedVoucher fixedVoucher) {
        List<Employee> employeeList = employeeService.list()
        List<Employee> anotherEmployeeList = getFixedVoucherListInDate(fixedVoucher.date).employee

        employeeList - (anotherEmployeeList - fixedVoucher.employee)
    }

    private List<FixedVoucher> getFixedVoucherListInDate(final Date date) {
        FixedVoucher.where { date == date }.list()
    }

    private List<String> getServiceList(final List<Map> services) {
        List<String> serviceList = services.inject([]) { accumulator, currentValue ->
            if (currentValue.value) accumulator << currentValue.label

            accumulator
        }

        serviceList
    }
}
