package ni.edu.ucc.leon.ticket

import grails.plugin.springsecurity.SpringSecurityService

class EmployeeTicketInterceptor {

    SpringSecurityService springSecurityService

    int order = HIGHEST_PRECEDENCE

    EmployeeTicketInterceptor() {
        match(controller: 'ticket', action: ~/(summaryInYearAndMonth|create|summary|summaryInYear|edit|save|index|delete|patch|update|show)/)
    }

    boolean before() {
        if (springSecurityService.currentUser.employee.id != params.long('employeeId')) {
            response.sendError 403
        }

        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
