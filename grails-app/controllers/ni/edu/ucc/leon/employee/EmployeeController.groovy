package ni.edu.ucc.leon.employee

import grails.validation.ValidationException
import ni.edu.ucc.leon.EmployeeService
import ni.edu.ucc.leon.Employee

class EmployeeController {

    @Autowired EmployeeService employeeService

    static allowedMethods = [ save: 'POST', update: 'PUT' ]

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
}
