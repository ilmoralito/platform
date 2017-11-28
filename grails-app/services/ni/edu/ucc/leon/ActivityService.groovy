package ni.edu.ucc.leon

import org.hibernate.transform.AliasToEntityMapResultTransformer
import ni.edu.ucc.leon.activity.UpdateActivityCommand
import ni.edu.ucc.leon.activity.SaveActivityCommand
import ni.edu.ucc.leon.AdministrativeActivity
import ni.edu.ucc.leon.CoordinationService
import ni.edu.ucc.leon.AcademicActivity
import ni.edu.ucc.leon.EmployeeService
import ni.edu.ucc.leon.CustomerService
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

    Activity save(SaveActivityCommand command)

    Activity update(UpdateActivityCommand command)

    Number countRequiringAttention(final String state, final Serializable employeeId)

    Number countRequiringAttention(final String state)

    List<Map> listRequiringAttention(final String state, final Serializable employeeId)

    List<Map> listRequiringAttention(final String state)
}

@Service(Activity)
abstract class ActivityService implements IActivityService {

    CustomerService customerService
    EmployeeService employeeService
    CoordinationService coordinationService
    @Autowired org.hibernate.SessionFactory sessionFactory

    @Override
    Activity delete(final Serializable id) {
        Activity activity = find(id)

        if (activity) {
            activity.locations.each { location ->
                TableLinen.removeAll(location)
            }

            activity.locations.clear()
        }

        activity.delete()

        activity
    }

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
    Activity save(SaveActivityCommand command) {
        Employee employee = employeeService.find(command.employeeId)
        Coordination coordination = coordinationService.find(command.organizedBy)

        if (command.customer) {
            new CustomerActivity(
                name: command.name,
                employee: employee,
                organizedBy: coordination,
                customer: command.customer
            ).save(failOnError: true)
        } else if (coordination.area in ['academic', 'school']) {
            new AcademicActivity(name: command.name, employee: employee, organizedBy: coordination).save(failOnError:true)
        } else {
            new AdministrativeActivity(name: command.name, employee: employee, organizedBy: coordination).save(failOnError:true)
        }
    }

    @Override
    Activity update(UpdateActivityCommand command) {
        Activity activity = find(command.id)

        if (activity) {
            activity.with {
                name = command.name
                organizedBy = coordinationService.find(command.organizedBy)

                if (command.customer) {
                    customer = customerService.find(command.customer)
                }

                save(flush: true)
            }
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

    @Override
    List<Map> listRequiringAttention(final String state) {
        final session = sessionFactory.currentSession
        final String query = '''
            SELECT
                a.id id, a.name name, c.name organizer
            FROM
                activities a
                    INNER JOIN
                coordinations c ON a.organized_by_id = c.id
            WHERE
                a.state = :state'''
        final sqlQuery = session.createSQLQuery(query)
        final results = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            setString('state', state)

            list()
        }

        results
    }

    @Override
    Number countRequiringAttention(final String state, final Serializable employeeId) {
        final session = sessionFactory.currentSession
        final String query = '''
            SELECT
                COUNT(a.id)
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
                        e.id = :employeeId)'''
        final sqlQuery = session.createSQLQuery(query)
        final result = sqlQuery.with {
            setString('state', state)
            setLong('employeeId', employeeId)

            uniqueResult()
        }

        result
    }

    @Override
    Number countRequiringAttention(final String state) {
        final session = sessionFactory.currentSession
        final String query = '''
            SELECT
                COUNT(a.id)
            FROM
                activities a
                    INNER JOIN
                coordinations c ON a.organized_by_id = c.id
            WHERE
                a.state = :state'''
        final sqlQuery = session.createSQLQuery(query)
        final result = sqlQuery.with {
            setString('state', state)

            uniqueResult()
        }

        result
    }
}
