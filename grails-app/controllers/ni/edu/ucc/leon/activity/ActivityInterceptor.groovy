package ni.edu.ucc.leon.activity

import ni.edu.ucc.leon.ActivityStateService
import ni.edu.ucc.leon.Activity

class ActivityInterceptor {

    ActivityStateService activityStateService

    int order = 1

    ActivityInterceptor() {
        match(controller: 'activity', action: ~/(update|delete)/)
        match(controller: 'voucher', action: ~/(save|store|delete)/)
        match(controller: 'refreshment', action: ~/(save|update|delete)/)
        match(controller: 'location', action: ~/(save|update|delete|clone)/)
    }

    boolean before() {
        // Long id = controllerName == 'activity' ? params.long('id') : params.long('activityId')
        // Activity activity = Activity.get(id)

        // if (activity.state == 'created' && activityStateService.isActivityCreator(activity) == false) {
        //     response.sendError 403

        //     return false
        // }

        // println activity.state // notified
        // println activityStateService.isValidAuthority('ROLE_ACADEMIC_COORDINATOR') // false
        // println activityStateService.isValidCoordination(activity.organizedBy) // true

        // if (activity.state == 'notified' && activityStateService.isValidAuthority('ROLE_ACADEMIC_COORDINATOR') == false && activityStateService.isValidCoordination(activity.organizedBy) == false) {
        //     response.sendError 403

        //     return false
        // }

        // if (activity.state == 'confirmed' && activityStateService.isValidAuthority('ROLE_ACADEMIC_DIRECTOR') == false) {
        //     response.sendError 404

        //     return false
        // }

        // if (activity.state == 'approved' && activityStateService.isValidAuthority('ROLE_ADMINISTRATIVE_DIRECTOR') == false) {
        //     response.sendError 404

        //     return false
        // }

        // if (activity.state in ['authorized', 'attended', 'canceled']) {
        //     response.sendError 403

        //     return false
        // }

        true
    }

    boolean after() { true }

    void afterView() {}
}
