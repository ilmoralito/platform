package ni.edu.ucc.leon.activity

import grails.plugin.springsecurity.SpringSecurityService
import ni.edu.ucc.leon.EmployeeCoordination
import ni.edu.ucc.leon.CoordinationService
import ni.edu.ucc.leon.EmployeeService
import ni.edu.ucc.leon.Coordination
import ni.edu.ucc.leon.Employee

class SaveActivityCommand implements grails.validation.Validateable {

    SpringSecurityService springSecurityService
    CoordinationService coordinationService
    EmployeeService employeeService

    Long employeeId
    String name
    Long organizedBy
    Long customer

    static constraints = {
        employeeId nullable: false
        name blank: false
        organizedBy nullable: false, validator: { organizedBy, obj ->
            Employee employee = obj.employeeService.find(obj.employeeId)
            Coordination coordination = obj.coordinationService.find(organizedBy)

            EmployeeCoordination.exists(employee, coordination)
        }
        customer nullable: true, validator: { customer, obj ->
            if (customer && !('ROLE_PROTOCOL' in obj.springSecurityService.currentUser.authorities.authority)) return false
        }
    }
}