package ni.edu.ucc.leon.activity

import ni.edu.ucc.leon.ActivityStateService
import ni.edu.ucc.leon.ActivityService
import ni.edu.ucc.leon.Activity

class ActivityInterceptor {

    int order = 1

    ActivityStateService activityStateService
    ActivityService activityService

    ActivityInterceptor() {
        match(controller: 'activity', action: ~/(update|delete)/)
        match(controller: 'location', action: ~/(save|update|delete|clone)/)
        match(controller: 'refreshment', action: ~/(save|update|delete)/)
    }

    boolean before() {
        Activity activity = activityService.find(params.id ?: params.activityId)

        if (activity.state == 'created' && activityStateService.isActivityCreator(activity) == false) {
            response.sendError 403

            return false
        }

        if (activity.state == 'notified' && activityStateService.isValidAuthority('ROLE_COORDINATOR') == false && activityStateService.isValidCoordination(activity.organizedBy) == false) {
            response.sendError 403

            return false
        }

        if (activity.state == 'confirmed' && activityStateService.isValidAuthority('ROLE_ACADEMIC_COORDINATOR') == false) {
            response.sendError 404

            return false
        }

        if (activity.state == 'approved' && activityStateService.isValidAuthority('ROLE_ADMINISTRATIVE_COORDINATOR') == false) {
            response.sendError 404

            return false
        }

        if (activity.state in ['authorized', 'attended', 'canceled']) {
            response.sendError 403

            return false
        }

        true
    }

    boolean after() { true }

    void afterView() {}
}
