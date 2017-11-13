package ni.edu.ucc.leon.activity

import ni.edu.ucc.leon.EmployeeCoordinationService
import grails.validation.ValidationException
import ni.edu.ucc.leon.EmployeeCoordination
import ni.edu.ucc.leon.ActivityService
import ni.edu.ucc.leon.EmployeeService
import ni.edu.ucc.leon.Activity
import ni.edu.ucc.leon.Employee
import ni.edu.ucc.leon.Helper

class ActivityController {

    EmployeeCoordinationService employeeCoordinationService
    EmployeeService employeeService
    ActivityService activityService

    static allowedMethods = [ save: 'POST', update: 'PUT', delete: 'DELETE', sendNotification: 'PUT' ]

    def index(final Long employeeId) {
        respond ([activityList: activityService.listByEmployeeAndState(employeeId, 'created')],
            model: [
                stateList: Helper.ACTIVITY_STATE_LIST,
                number: countRequiringAttention(employeeId)
            ]
        )
    }

    def create(final Long employeeId) {
        [
            nameList: activityService.activityNamesPerEmployee(employeeId),
            employeeCoordinations: employeeCoordinationService.listByEmployee(employeeService.find(employeeId))
        ]
    }

    def save(final Long employeeId, final String name, final Long organizedBy) {
        try {
            Activity activity = activityService.save(employeeId, name, organizedBy)

            flash.message = 'Actividad creada'
            redirect uri: "/employees/${employeeId}/activities/${activity.id}", method: 'GET'
        } catch(ValidationException e) {
            render view: 'create', model: [
                errors: e.errors,
                nameList: activityService.activityNamesPerEmployee(employeeId),
                employeeCoordinations: employeeCoordinationService.listByEmployee(employeeService.find(employeeId))
            ]
        }
    }

    def show(final Long id, final Long employeeId) {
        Activity activity = activityService.find(id)

        [activity: activity]
    }

    def edit(final Long id, final Long employeeId) {
        Employee employee = employeeService.find(employeeId)

        [
            activity: activityService.find(id),
            nameList: activityService.activityNamesPerEmployee(employeeId),
            employeeCoordinations: employeeCoordinationService.listByEmployee(employee)
        ]
    }

    def update(final Long id, final Long employeeId, final String name, final Long organizedBy) {
        try {
            Activity activity = activityService.update(id, name, organizedBy)

            if (!activity) {
                notFound(employeeId)

                return
            }

            flash.message = 'Actividad actualizada'
            redirect uri: "/employees/${employeeId}/activities/${activity.id}", method: 'GET'
        } catch(ValidationException e) {
            render view: 'edit', model: [
                errors: e.errors,
                activity: activityService.find(id),
                nameList: activityService.activityNamesPerEmployee(employeeId),
                employeeCoordinations: employeeCoordinationService.listByEmployee(employeeService.find(employeeId))
            ]
        }
    }

    def delete(final Long id, final Long employeeId) {
        Activity activity = activityService.delete(id)

        if (!activity) {
            notFound(employeeId)

            return
        }

        flash.message = 'Actividad eliminada'
        redirect uri: "/employees/$employeeId/activities", method: 'GET'
    }

    def sendNotification(final Long employeeId, final Long activityId, final String state) {
        Activity activity = activityService.find(activityId)

        if (!activity || !activity.locations) {
            flash.message = 'Ubicaciones son necesarias para continuar'

            redirect uri: "/employees/$employeeId/activities/$activityId", method: 'GET'
            return
        }

        activityService.updateState(state ?: getNextNotificationState(), activityId)

        flash.message = 'Estado actualizado'
        redirect uri: "/employees/$employeeId/activities/$activityId", method: 'GET'
    }

    def filter(final Long employeeId, final String state) {
        respond (
            [activityList: activityService.listByEmployeeAndState(employeeId, state)],
            model: [
                stateList: Helper.ACTIVITY_STATE_LIST,
                number: activityService.countRequiringAttention(getNotificationState(), employeeId)
            ],
            view: 'index'
        )
    }

    def requiringAttention(final Long employeeId) {
        final List<Map> results = listRequiringAttention(employeeId)
        final List<Map> activityListByOrganizer = results.groupBy { it.organizer }.collect {
            [organizer: it.key, activities: it.value.collect {
                [id: it.id, name: it.name]
            }]
        }

        respond ([activityListByOrganizer: activityListByOrganizer],
            model: [
                stateList: Helper.ACTIVITY_STATE_LIST,
                number: countRequiringAttention(employeeId)
            ]
        )
    }

    protected void notFound(final Long employeeId) {
        flash.message = 'Actividad no encontrada'

        redirect uri: "/employees/$employeeId/activities", method: 'GET'
    }

    private final String getNotificationState() {
        List<String> authorityList = authenticatedUser.authorities.authority

        if ('ROLE_COORDINATOR' in authorityList) return 'notified'

        if ('ROLE_ACADEMIC_COORDINATOR' in authorityList) return 'confirmed'

        if ('ROLE_ADMINISTRATIVE_COORDINATOR' in authorityList) return 'approved'

        if ('ROLE_PROTOCOL' in authorityList) return 'authorized'
    }

    private final String getNextNotificationState() {
        List<String> authorityList = authenticatedUser.authorities.authority

        if ('ROLE_ASSISTANT' in authorityList) return 'notified'

        if ('ROLE_COORDINATOR' in authorityList) return 'confirmed'

        if ('ROLE_ACADEMIC_COORDINATOR' in authorityList) return 'approved'

        if ('ROLE_ADMINISTRATIVE_COORDINATOR' in authorityList) return 'authorized'

        if ('ROLE_PROTOCOL' in authorityList) return 'attended'
    }

    private final Boolean isSupervisor() {
        final List<String> supervisorList = ['ROLE_ACADEMIC_COORDINATOR', 'ROLE_ADMINISTRATIVE_COORDINATOR', 'ROLE_PROTOCOL']
        final List<String> authorityList = authenticatedUser.authorities.authority

        supervisorList.any { authority -> authority in authorityList }
    }

    private final List<Map> listRequiringAttention(final Long employeeId) {
        final String state = getNotificationState()

        if (isSupervisor()) return activityService.listRequiringAttention(state)

        activityService.listRequiringAttention(state, employeeId)
    }

    private final Number countRequiringAttention(final Long employeeId) {
        final String state = getNotificationState()

        if (isSupervisor()) return activityService.countRequiringAttention(state)

        activityService.countRequiringAttention(state, employeeId)
    }
}
