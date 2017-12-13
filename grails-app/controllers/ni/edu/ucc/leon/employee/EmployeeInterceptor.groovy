package ni.edu.ucc.leon.employee

import grails.plugin.springsecurity.SpringSecurityService
import ni.edu.ucc.leon.EmployeeCoordination
import ni.edu.ucc.leon.Coordination
import ni.edu.ucc.leon.Employee

class EmployeeInterceptor {

    SpringSecurityService springSecurityService

    EmployeeInterceptor() {
        match(controller: 'employee', action: ~/(guests|store)/)
    }

    boolean before() {
        Employee employee = Employee.get(params?.employeeId)

        if (employee?.id != springSecurityService.currentUser.employee.id) {
            response.sendError 403

            return false
        }

        if (actionName == 'store') {
            List<Coordination> coordinationList = EmployeeCoordination.listEmployeeCoordinations(employee)
            Coordination coordination = Coordination.findByName(params.coordination)

            if (!(coordination in coordinationList)) {
                response.sendError 403

                return false
            }
        }

        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
