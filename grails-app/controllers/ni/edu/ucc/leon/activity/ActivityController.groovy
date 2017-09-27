package ni.edu.ucc.leon.activity

import ni.edu.ucc.leon.EmployeeCoordinationService
import grails.validation.ValidationException
import ni.edu.ucc.leon.EmployeeCoordination
import ni.edu.ucc.leon.ActivityService
import ni.edu.ucc.leon.EmployeeService
import ni.edu.ucc.leon.Activity
import ni.edu.ucc.leon.Employee

class ActivityController {

    @Autowired EmployeeCoordinationService employeeCoordinationService
    @Autowired EmployeeService employeeService
    @Autowired ActivityService activityService

    static allowedMethods = [ save: 'POST', update: 'PUT', delete: 'DELETE' ]

    def index(final Long employeeId) {
        [activityList: activityService.listByEmployeeAndState(employeeId, 'created')]
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
        Employee employee = employeeService.find(id)

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
                notFound()
            } else {
                flash.message = 'Actividad actualizada'
                redirect uri: "/employees/${employeeId}/activities/${activity.id}", method: 'GET'
            }
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

        if (!activity) notFound()

        flash.message = 'Actividad eliminada'
        redirect uri: "/employees/$employeeId/activities", method: 'GET'
    }

    protected void notFound(final Long id, final Long employeeId) {
        flash.message = 'Actividad no encontrada'
    }
}
