package ni.edu.ucc.leon.activity

import grails.plugin.springsecurity.SpringSecurityService

class ActivityInterceptor {

    SpringSecurityService springSecurityService

    ActivityInterceptor() {
        match(controller: 'activity', action: '*')
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
