package ni.edu.ucc.leon.employee

import grails.validation.ValidationException
import ni.edu.ucc.leon.EmployeeCoordination
import ni.edu.ucc.leon.CoordinationGuest
import ni.edu.ucc.leon.EmployeeService
import ni.edu.ucc.leon.GuestService
import ni.edu.ucc.leon.Coordination
import ni.edu.ucc.leon.Employee
import ni.edu.ucc.leon.Guest

class EmployeeController {

    EmployeeService employeeService
    GuestService guestService

    static allowedMethods = [save: 'POST', update: 'PUT', updateFullName: 'PUT', store: 'POST']

    def index() {
        respond employeeService.list()
    }

    def create() {
        respond new Employee(params)
    }

    def save(String fullName, String identityCard, String contract) {
        try {
            Employee employee = employeeService.save(fullName, identityCard, contract)

            flash.message = 'Empleado creado'
            redirect employee
        } catch(ValidationException e) {
            respond e.errors, view: 'create'
        }
    }

    def show(Long id) {
        respond id ? employeeService.find(id) : null
    }

    def edit(Long id) {
        respond id ? employeeService.find(id) : null
    }

    def update(Long id, String fullName, String identityCard, String contract, Boolean enabled) {
        try {
            Employee employee = employeeService.update(id, fullName, identityCard, contract, enabled ?: false)

            if (!employee) {
                notFound()
            } else {
                flash.message = 'Empleado actualizado'
                redirect employee
            }
        } catch(ValidationException e) {
            respond e.errors, view: 'edit'
        }
    }

    def updateFullName() {
        Employee employee = employeeService.find(params.employeeId)

        if (employee) {
            try {
                employee.fullName = params.fullName
                employee.save(flush: true)

                respond employee, formats: ['json'], status: 200
            } catch(Exception e) {
                respond e.errors, formats: ['json']
            }

            return
        }

        respond formats: ['json'], status: 404
    }

    def guests(final Long employeeId, final String coordination) {
        Employee employee = employeeService.find(employeeId)
        Coordination coordinationInstance = coordination ? Coordination.findByName(coordination) : null
        List<Coordination> coordinationList = EmployeeCoordination.listEmployeeCoordinations(employee)
        List<Guest> coordinationGuestList = CoordinationGuest.guestList(coordinationInstance ?: coordinationList[0])

        respond ([guestList: guestService.findAll()], model: [coordinationList: coordinationList, coordinationGuestList: coordinationGuestList])
    }

    def store(SaveGuestCommand command) {
        if (command.hasErrors()) {
            Employee employee = employeeService.find(command.employeeId)
            Coordination coordination = Coordination.findByName(command.coordination)

            respond ([errors: command.errors], model: [
                coordinationList: EmployeeCoordination.listEmployeeCoordinations(employee),
                coordinationGuestList: CoordinationGuest.guestList(coordination),
                guestList: guestService.findAll()
            ], view: 'guests')

            return
        }

        Coordination coordination = Coordination.findByName(command.coordination)
        CoordinationGuest.removeAll(coordination)

        command.guestList.each {
            Guest guest = Guest.get(it)

            CoordinationGuest.create(coordination, guest)
        }

        flash.message = 'Lista de visita actualizada'
        redirect uri: "/employees/${command.employeeId}/guests/${command.coordination}", method: 'GET'
    }
}
