package ni.edu.ucc.leon

import org.hibernate.transform.AliasToEntityMapResultTransformer
import ni.edu.ucc.leon.AdministrativeActivity
import ni.edu.ucc.leon.CoordinationService
import ni.edu.ucc.leon.AcademicActivity
import ni.edu.ucc.leon.EmployeeService
import grails.gorm.services.Service
import org.hibernate.SessionFactory
import grails.gorm.services.Query
import ni.edu.ucc.leon.Activity

interface IActivityService {

    Activity find(final Serializable id)

    Activity delete(final Serializable id)

    @Query("UPDATE ${Activity activity} SET ${activity.state} = $state WHERE activity.id = $id")
    void updateState(final String state, final Serializable id)

    List<Map> activityNamesPerEmployee(final Serializable employeeId)

    List<Activity> listByEmployeeAndState(final Serializable employeeId, final String state)

    Activity update(final Serializable id, final String name, final Serializable coordinationId)

    Activity save(final Serializable employeeId, final String name, final Serializable coordinationId)

    List<Map> listRequiringAttention(final String state, final Serializable employeeId)
}

@Service(Activity)
abstract class ActivityService implements IActivityService {

    CoordinationService coordinationService
    EmployeeService employeeService

     @Autowired SessionFactory sessionFactory

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

    @Override
    List<Map> listRequiringAttention(final String state, final Serializable employeeId) {
        final session = sessionFactory.currentSession
        final String query = '''
            SELECT 
                a.id id, a.name name, c.name organizer
            FROM
                activities a
                    INNER JOIN
                coordinations c ON a.organized_by_id = c.id
            WHERE
                a.state = :state
                    AND a.organized_by_id IN (SELECT 
                        ec.coordination_id
                    FROM
                        employee_coordinations ec
                            INNER JOIN
                        coordinations c ON ec.coordination_id = c.id
                            INNER JOIN
                        employees e ON e.id = ec.employee_id
                    WHERE
                        e.id = :employeeId)
        '''
        final sqlQuery = session.createSQLQuery(query)
        final results = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            setString('state', state)
            setLong('employeeId', employeeId)

            list()
        }

        results
    }
}
