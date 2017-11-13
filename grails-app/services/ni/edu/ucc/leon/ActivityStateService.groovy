package ni.edu.ucc.leon

import grails.plugin.springsecurity.SpringSecurityService

class ActivityStateService {

    SpringSecurityService springSecurityService

    Boolean isActivityCreator(final Activity activity) {
        springSecurityService.currentUser.employee.is(activity.employee)
    }

    Boolean isValidAuthority(final String authority) {
        authority in getCurrentUserAuthorities()
    }

    Boolean isValidCoordination(final Coordination coordination) {
        coordination in EmployeeCoordination.listEmployeeCoordinations(springSecurityService.currentUser.employee)
    }

    private List<String> getCurrentUserAuthorities() {
        springSecurityService.currentUser.authorities.authority
    }
}
