package ni.edu.ucc.leon

import ni.edu.ucc.leon.AdministrativeActivity
import ni.edu.ucc.leon.CoordinationService
import ni.edu.ucc.leon.AcademicActivity
import ni.edu.ucc.leon.EmployeeService
import grails.gorm.services.Service
import ni.edu.ucc.leon.Activity

interface IActivityService {

    Activity find(final Serializable id)

    Activity delete(final Serializable id)

    List<Activity> listByEmployeeAndState(final Serializable employeeId, final String state)

    List<Map> activityNamesPerEmployee(final Serializable employeeId)

    Activity save(final Serializable employeeId, final String name, final Serializable coordinationId)

    Activity update(final Serializable id, final String name, final Serializable coordinationId)
}

@Service(Activity)
abstract class ActivityService implements IActivityService {

    CoordinationService coordinationService
    EmployeeService employeeService

    @Override
    List<Activity> listByEmployeeAndState(final Serializable employeeId, final String state) {
        Activity.where { employee.id == employeeId && state == state }.list()
    }

    @Override
    List<Map> activityNamesPerEmployee(final Serializable employeeId) {
        List<Map> activityNames = Activity.executeQuery('''
            SELECT
                DISTINCT new map(a.name AS name)
            FROM Activity a
            WHERE employee.id = :employeeId''',
            [employeeId: employeeId]
        )

        activityNames
    }

    @Override
    Activity save(final Serializable employeeId, final String name, final Serializable coordinationId) {
        Employee employee = employeeService.find(employeeId)
        Coordination coordination = coordinationService.find(coordinationId)

        if (coordination.area in ['academic', 'school']) {
            new AcademicActivity(name: name, employee: employee, organizedBy: coordination).save(failOnError:true)
        } else {
            new AdministrativeActivity(name: name, employee: employee, organizedBy: coordination).save(failOnError:true)
        }
    }

    @Override
    Activity update(final Serializable id, final String name, final Serializable coordinationId) {
        Activity activity = find(id)
        Coordination coordination = coordinationService.find(coordinationId)

        if (activity && coordination) {
            activity.name = name
            activity.organizedBy = coordination

            activity.save(failOnError: true)
        }

        activity
    }
}
