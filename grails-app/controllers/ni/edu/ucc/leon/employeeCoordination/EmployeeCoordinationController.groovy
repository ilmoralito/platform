package ni.edu.ucc.leon.employeeCoordination

import static org.springframework.http.HttpStatus.NOT_FOUND
import grails.validation.ValidationException

import ni.edu.ucc.leon.EmployeeCoordinationService
import ni.edu.ucc.leon.CoordinationService
import ni.edu.ucc.leon.EmployeeService

import ni.edu.ucc.leon.EmployeeCoordination
import ni.edu.ucc.leon.Coordination
import ni.edu.ucc.leon.Employee

class EmployeeCoordinationController {
    @Autowired EmployeeCoordinationService employeeCoordinationService
    @Autowired CoordinationService coordinationService
    @Autowired EmployeeService employeeService

    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def index(Long employeeId) {
        Employee employee = employeeService.find(employeeId)

        respond employeeCoordinationService.listByEmployee(employee), model: [
            coordinationList: employee.coordinations.name ? coordinationService.listNotInList(employee.coordinations.name) : coordinationService.list()
        ]
    }

    def save(EmployeeCoordinationSaveCommand command, Long employeeId) {
        Employee employee = employeeService.find(employeeId)

        if (command.hasErrors()) {
            respond command.errors, model: [employeeCoordinationList: employeeCoordinationService.listByEmployee(employee), coordinationList: coordinationService.listNotInList(employee.coordinations.name)], view: 'index'
        } else {
            try {
                EmployeeCoordination employeeCoordination = employeeCoordinationService.save(employeeId, command.coordinationId, command.position, command.jobTitle)

                flash.message = 'Coordinacion agregada'
                redirect uri: "/employees/$employeeId/employeeCoordinations", method: 'GET'
            } catch(ValidationException e) {
                respond e.errors, model: [employeeCoordinationList: employeeCoordinationService.listByEmployee(employee), coordinationList: coordinationService.listNotInList(employee.coordinations.name)], view: 'index'
            }
        }
    }

    def edit(Long employeeId, Long id) {
        Employee employee = employeeService.find(employeeId)
        EmployeeCoordination employeeCoordination = employeeCoordinationService.find(id)

        if (!employee || !employeeCoordination) {
            flash.message = 'Parametros incorrectos'
            redirect uri: '/employees', method: 'GET'
        } else {
            respond employeeCoordination, model: [
                coordinationList: employee.coordinations.size() != 1 ? coordinationService.listNotInList(employee.coordinations.name - employeeCoordination.coordination.name) : coordinationService.list()
            ]
        }
    }

    def update(EmployeeCoordinationUpdateCommand command, Long employeeId) {
        if (command.hasErrors()) {
            render command.errors
            return
        } else {
            try {
                Employee employee = employeeService.find(employeeId)
                Coordination coordination = coordinationService.find(command.coordinationId)
                EmployeeCoordination userCoordination = employeeCoordinationService.update(command.id, employee, coordination, command.position, command.jobTitle)

                flash.message = 'Coordinacion de usuario actualizado'
                redirect uri: "/employees/$employeeId/employeeCoordinations", method: 'GET'
            } catch(ValidationException e) {
                Employee employee = employeeService.find(employeeId)

                respond e.errors, model: [coordinationList: coordinationService.listNotInList(employee.coordinations.name - employeeCoordination.coordination.name)]
            }
        }
    }

    def delete(Long employeeId, Long id) {
        EmployeeCoordination employeeCoordination = employeeCoordinationService.delete(id)

        if (employeeCoordination == null) {
            notFound()
        } else {
            flash.message = 'Coordinacion eliminada'
            redirect uri: "/employees/$employeeId/employeeCoordinations", method: 'GET'
        }
    }

    protected notFound(Long employeeId) {
        flash.message = 'No encontrado'
        redirect uri "/employees/$employeeId/employeeCoordinations"
    }
}
