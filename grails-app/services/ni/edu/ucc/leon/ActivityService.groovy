package ni.edu.ucc.leon

import org.hibernate.transform.AliasToEntityMapResultTransformer
import grails.plugin.springsecurity.SpringSecurityService
import org.hibernate.SessionFactory
import grails.gorm.services.Query

import ni.edu.ucc.leon.activity.UpdateActivityCommand
import ni.edu.ucc.leon.activity.SaveActivityCommand

import ni.edu.ucc.leon.CoordinationService
import ni.edu.ucc.leon.EmployeeService
import ni.edu.ucc.leon.CustomerService
import grails.gorm.services.Service

import ni.edu.ucc.leon.AdministrativeActivity
import ni.edu.ucc.leon.AcademicActivity
import ni.edu.ucc.leon.Activity

interface IActivityService {

    @Query("""
        SELECT NEW MAP
            (
                a.id AS id,
                a.name AS name,
                a.state AS state,
                c.name AS coordination,
                DATE_FORMAT(a.dateCreated, '%Y-%m-%d') AS dateCreated
            )
        FROM
            ${Activity a}
                INNER JOIN
            ${Coordination c} ON a.organizedBy.id = c.id
        WHERE
            MONTH(a.dateCreated) = $month""")
    List<Map> getSummaryDetail(final Integer month)

    @Query("""
        SELECT NEW MAP
            (
                a.id AS id,
                a.name AS name,
                a.state AS state,
                c.name AS coordination,
                DATE_FORMAT(a.dateCreated, '%Y-%m-%d') AS dateCreated
            )
        FROM
            ${Activity a}
                INNER JOIN
            ${Coordination c} ON a.organizedBy.id = c.id
        WHERE
            MONTH(a.dateCreated) = $month AND YEAR(a.dateCreated) = $year""")
    List<Map> getSummaryDetail(final Integer month, final Integer year)

    List<Map> getSummary()

    List<Map> getSummary(final Integer year)

    List<Map> getSummary(final Serializable employeeId)

    List<Map> getSummary(final Serializable employeeId, final Integer year)

    @Query("SELECT DISTINCT(YEAR($a.dateCreated)) AS year FROM ${Activity a} ORDER BY 1 DESC")
    List<Number> yearList()

    @Query("SELECT DISTINCT(YEAR($a.dateCreated)) AS year FROM ${Activity a} INNER JOIN ${Employee e} ON a.employee.id = e.id WHERE e.id = $employeeId ORDER BY 1 DESC")
    List<Number> yearList(final Serializable employeeId)

    List<Activity> list(final Serializable employeeId)

    Activity find(final Serializable id)

    Activity delete(final Serializable id)

    void updateState(final String state, final Serializable id)

    List<Map> activityNamesPerEmployee(final Serializable employeeId)

    Activity save(SaveActivityCommand command)

    Activity update(UpdateActivityCommand command)

    List<Map> listRequiringAttention(final String state, final Serializable employeeId)

    List<Map> listRequiringAttention(final Serializable coordinationId)

    List<Map> listRequiringAttention(final String state)

    List<Map> listRequiringAttention()

    Number countRequiringAttention(final String state, final Serializable employeeId)

    Number countRequiringAttention(final Serializable coordinationId)

    Number countRequiringAttention(final String state)

    Number countRequiringAttention()
}

@Service(Activity)
abstract class ActivityService implements IActivityService {

    @Autowired org.hibernate.SessionFactory sessionFactory
    @Autowired SpringSecurityService springSecurityService
    CoordinationService coordinationService
    CustomerService customerService
    EmployeeService employeeService

    @Override
    List<Map> getSummary() {
        final session = sessionFactory.currentSession
        final String query = """
            SELECT
                s.name month,
                s.m pivot,
                SUM(CASE WHEN a.state = 'created' THEN 1 ELSE 0 END) created,
                SUM(CASE WHEN a.state = 'notified' THEN 1 ELSE 0 END) notified,
                SUM(CASE WHEN a.state = 'confirmed' THEN 1 ELSE 0 END) confirmed,
                SUM(CASE WHEN a.state = 'approved' THEN 1 ELSE 0 END) approved,
                SUM(CASE WHEN a.state = 'authorized' THEN 1 ELSE 0 END) authorized,
                SUM(CASE WHEN a.state = 'attended' THEN 1 ELSE 0 END) attended,
                SUM(CASE WHEN a.state = 'canceled' THEN 1 ELSE 0 END) canceled,
                COUNT(a.id) total
            FROM (
                  SELECT  1 m, 'Jan' AS name
                  UNION SELECT 2, 'Feb'
                  UNION SELECT 3, 'Mar'
                  UNION SELECT 4, 'Apr'
                  UNION SELECT 5, 'May'
                  UNION SELECT 6, 'Jun'
                  UNION SELECT 7, 'Jul'
                  UNION SELECT 8, 'Aug'
                  UNION SELECT 9, 'Sep'
                  UNION SELECT 10, 'Oct'
                  UNION SELECT 11, 'Nov'
                  UNION SELECT 12, 'Dec'
            ) s
            LEFT JOIN (activities a INNER JOIN employees e ON a.employee_id = e.id) ON (s.m = MONTH(a.date_created))
            GROUP BY 1, 2 ORDER BY pivot DESC"""
        final sqlQuery = session.createSQLQuery(query)
        final result = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            list()
        }

        result
    }

    @Override
    List<Map> getSummary(final Integer year) {
        final session = sessionFactory.currentSession
        final String query = """
            SELECT
                s.name month,
                s.m pivot,
                SUM(CASE WHEN a.state = 'created' THEN 1 ELSE 0 END) created,
                SUM(CASE WHEN a.state = 'notified' THEN 1 ELSE 0 END) notified,
                SUM(CASE WHEN a.state = 'confirmed' THEN 1 ELSE 0 END) confirmed,
                SUM(CASE WHEN a.state = 'approved' THEN 1 ELSE 0 END) approved,
                SUM(CASE WHEN a.state = 'authorized' THEN 1 ELSE 0 END) authorized,
                SUM(CASE WHEN a.state = 'attended' THEN 1 ELSE 0 END) attended,
                SUM(CASE WHEN a.state = 'canceled' THEN 1 ELSE 0 END) canceled,
                COUNT(a.id) total
            FROM (
                  SELECT  1 m, 'Jan' AS name
                  UNION SELECT 2, 'Feb'
                  UNION SELECT 3, 'Mar'
                  UNION SELECT 4, 'Apr'
                  UNION SELECT 5, 'May'
                  UNION SELECT 6, 'Jun'
                  UNION SELECT 7, 'Jul'
                  UNION SELECT 8, 'Aug'
                  UNION SELECT 9, 'Sep'
                  UNION SELECT 10, 'Oct'
                  UNION SELECT 11, 'Nov'
                  UNION SELECT 12, 'Dec'
            ) s
            LEFT JOIN (activities a INNER JOIN employees e ON a.employee_id = e.id AND YEAR(a.date_created) = :year) ON (s.m = MONTH(a.date_created))
            GROUP BY 1, 2 ORDER BY pivot DESC"""
        final sqlQuery = session.createSQLQuery(query)
        final result = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            setInteger('year', year)

            list()
        }

        result
    }

    @Override
    List<Map> getSummary(final Serializable employeeId) {
        final session = sessionFactory.currentSession
        final String query = """
            SELECT
                s.name month,
                s.m pivot,
                SUM(CASE WHEN a.state = 'created' THEN 1 ELSE 0 END) created,
                SUM(CASE WHEN a.state = 'notified' THEN 1 ELSE 0 END) notified,
                SUM(CASE WHEN a.state = 'confirmed' THEN 1 ELSE 0 END) confirmed,
                SUM(CASE WHEN a.state = 'approved' THEN 1 ELSE 0 END) approved,
                SUM(CASE WHEN a.state = 'authorized' THEN 1 ELSE 0 END) authorized,
                SUM(CASE WHEN a.state = 'attended' THEN 1 ELSE 0 END) attended,
                SUM(CASE WHEN a.state = 'canceled' THEN 1 ELSE 0 END) canceled,
                COUNT(a.id) total
            FROM (
                  SELECT  1 m, 'Jan' AS name
                  UNION SELECT 2, 'Feb'
                  UNION SELECT 3, 'Mar'
                  UNION SELECT 4, 'Apr'
                  UNION SELECT 5, 'May'
                  UNION SELECT 6, 'Jun'
                  UNION SELECT 7, 'Jul'
                  UNION SELECT 8, 'Aug'
                  UNION SELECT 9, 'Sep'
                  UNION SELECT 10, 'Oct'
                  UNION SELECT 11, 'Nov'
                  UNION SELECT 12, 'Dec'
            ) s
            LEFT JOIN (activities a INNER JOIN employees e ON a.employee_id = e.id AND e.id = :employeeId) ON (s.m = MONTH(a.date_created))
            GROUP BY 1, 2 ORDER BY pivot DESC"""
        final sqlQuery = session.createSQLQuery(query)
        final result = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            setLong('employeeId', employeeId)

            list()
        }

        result
    }

    @Override
    List<Map> getSummary(final Serializable employeeId, final Integer year) {
        final session = sessionFactory.currentSession
        final String query = """
            SELECT
                s.name month,
                s.m pivot,
                SUM(CASE WHEN a.state = 'created' THEN 1 ELSE 0 END) created,
                SUM(CASE WHEN a.state = 'notified' THEN 1 ELSE 0 END) notified,
                SUM(CASE WHEN a.state = 'confirmed' THEN 1 ELSE 0 END) confirmed,
                SUM(CASE WHEN a.state = 'approved' THEN 1 ELSE 0 END) approved,
                SUM(CASE WHEN a.state = 'authorized' THEN 1 ELSE 0 END) authorized,
                SUM(CASE WHEN a.state = 'attended' THEN 1 ELSE 0 END) attended,
                SUM(CASE WHEN a.state = 'canceled' THEN 1 ELSE 0 END) canceled,
                COUNT(a.id) total
            FROM (
                  SELECT  1 m, 'Jan' AS name
                  UNION SELECT 2, 'Feb'
                  UNION SELECT 3, 'Mar'
                  UNION SELECT 4, 'Apr'
                  UNION SELECT 5, 'May'
                  UNION SELECT 6, 'Jun'
                  UNION SELECT 7, 'Jul'
                  UNION SELECT 8, 'Aug'
                  UNION SELECT 9, 'Sep'
                  UNION SELECT 10, 'Oct'
                  UNION SELECT 11, 'Nov'
                  UNION SELECT 12, 'Dec'
            ) s
            LEFT JOIN (activities a INNER JOIN employees e ON a.employee_id = e.id AND e.id = :employeeId AND YEAR(a.date_created) = :year) ON (s.m = MONTH(a.date_created))
            GROUP BY 1, 2 ORDER BY pivot DESC"""
        final sqlQuery = session.createSQLQuery(query)
        final result = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            setLong('employeeId', employeeId)
            setInteger('year', year)

            list()
        }

        result
    }

    @Override
    List<Map> list(final Serializable employeeId) {
        final session = sessionFactory.currentSession
        final query = """
            SELECT
                a.id id,
                a.name name,
                a.state state,
                IFNULL(totalLocations, 0) locations
            FROM
                activities a
                    INNER JOIN
                employees e ON a.employee_id = e.id
                    LEFT JOIN
                (SELECT
                    activity_id, COUNT(1) totalLocations
                FROM
                    locations
                GROUP BY activity_id) lo ON a.id = lo.activity_id
            WHERE
                e.id = :employeeId
                    AND state NOT IN ('attended' , 'canceled')"""
        final sqlQuery = session.createSQLQuery(query)
        final results = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            setLong('employeeId', employeeId)

            list()
        }

        results
    }

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
    void updateState(final String state, final Serializable id) {
        Activity activity = find(id)

        if (activity) {
            activity.state = state

            if (activity.instanceOf(AcademicActivity) && activity.state == 'confirmed') {
                activity.confirmedBy = springSecurityService.currentUser.employee
                activity.confirmationDate = new Date()
            }

            if (activity.instanceOf(AcademicActivity) && activity.state == 'approved') {
                activity.approvedBy = springSecurityService.currentUser.employee
                activity.approvalDate = new Date()
            }

            if (state == 'authorized') {
                activity.authorizedBy = springSecurityService.currentUser.employee
                activity.authorizationDate = new Date()
            }

            activity.save(flush: true)
        }
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
                a.id id, a.name name, c.name organizer, total_locations locations, total_vouchers vouchers
            FROM
                activities a
                    INNER JOIN
                coordinations c ON a.organized_by_id = c.id
                    LEFT JOIN
                (
                    SELECT activity_id, count(1) AS total_locations
                    FROM locations
                    GROUP BY activity_id
                ) lo ON a.id = lo.activity_id
                    LEFT JOIN
                (
                    SELECT activity_id, count(1) AS total_vouchers
                    FROM vouchers
                    GROUP BY activity_id
                ) vo ON a.id = vo.activity_id
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
    List<Map> listRequiringAttention(final Serializable coordinationId) {
        final session = sessionFactory.currentSession
        final String query = """
            (
                SELECT
                    a.id id, a.name name, c.name organizer, total_locations locations, total_vouchers vouchers
                FROM
                    activities a INNER JOIN coordinations c ON a.organized_by_id = c.id
                        LEFT JOIN
                    (
                        SELECT activity_id, count(1) AS total_locations
                        FROM locations
                        GROUP BY activity_id
                    ) lo ON a.id = lo.activity_id
                        LEFT JOIN
                    (
                        SELECT activity_id, count(1) AS total_vouchers
                        FROM vouchers
                        GROUP BY activity_id
                    ) vo ON a.id = vo.activity_id
                WHERE
                    a.state = 'confirmed'
            )
            UNION
            (
                SELECT
                    a.id id, a.name name, c.name organizer, total_locations locations, total_vouchers vouchers
                FROM
                    activities a INNER JOIN coordinations c ON a.organized_by_id = c.id
                    LEFT JOIN
                    (
                        SELECT activity_id, count(1) AS total_locations
                        FROM locations
                        GROUP BY activity_id
                    ) lo ON a.id = lo.activity_id
                        LEFT JOIN
                    (
                        SELECT activity_id, count(1) AS total_vouchers
                        FROM vouchers
                        GROUP BY activity_id
                    ) vo ON a.id = vo.activity_id
                WHERE
                    a.state = 'notified' and a.organized_by_id = :coordinationId
            )"""
        final sqlQuery = session.createSQLQuery(query)
        final results = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            setLong('coordinationId', coordinationId)

            list()
        }

        results
    }

    @Override
    List<Map> listRequiringAttention(final String state) {
        final session = sessionFactory.currentSession
        final String query = '''
            SELECT
                a.id id, a.name name, c.name organizer, total_locations locations, total_vouchers vouchers
            FROM
                activities a
                    INNER JOIN
                coordinations c ON a.organized_by_id = c.id
                    LEFT JOIN
                (
                    SELECT activity_id, count(1) AS total_locations
                    FROM locations
                    GROUP BY activity_id
                ) lo ON a.id = lo.activity_id
                    LEFT JOIN
                (
                    SELECT activity_id, count(1) AS total_vouchers
                    FROM vouchers
                    GROUP BY activity_id
                ) vo ON a.id = vo.activity_id
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
    List<Map> listRequiringAttention() {
        final session = sessionFactory.currentSession
        final String query = """
           (SELECT
               a.id id, a.name name, c.name organizer, total_locations locations, total_vouchers vouchers
           FROM
               activities a
                   INNER JOIN
               coordinations c ON a.organized_by_id = c.id
                   LEFT JOIN
                (
                    SELECT activity_id, count(1) AS total_locations
                    FROM locations
                    GROUP BY activity_id
                ) lo ON a.id = lo.activity_id
                    LEFT JOIN
                (
                    SELECT activity_id, count(1) AS total_vouchers
                    FROM vouchers
                    GROUP BY activity_id
                ) vo ON a.id = vo.activity_id
           WHERE
               a.state = 'approved') UNION (SELECT
               a.id id, a.name name, c.name organizer, total_locations locations, total_vouchers vouchers
           FROM
               activities a
                   INNER JOIN
               coordinations c ON a.organized_by_id = c.id
                   LEFT JOIN
                (
                    SELECT activity_id, count(1) AS total_locations
                    FROM locations
                    GROUP BY activity_id
                ) lo ON a.id = lo.activity_id
                    LEFT JOIN
                (
                    SELECT activity_id, count(1) AS total_vouchers
                    FROM vouchers
                    GROUP BY activity_id
                ) vo ON a.id = vo.activity_id
           WHERE
               a.state = 'notified'
                   AND c.area = 'Administrative')"""
        final sqlQuery = session.createSQLQuery(query)
        final result = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            list()
        }

        result
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
    Number countRequiringAttention(final Serializable coordinationId) {
        final session = sessionFactory.currentSession
        final String query = """
            SELECT
                COUNT(*)
            FROM
            (
                SELECT
                    a.id id, a.name name, c.name organizer
                FROM
                    activities a INNER JOIN coordinations c ON a.organized_by_id = c.id
                WHERE
                    a.state = 'confirmed'
                UNION
                SELECT
                    a.id id, a.name name, c.name organizer
                FROM
                    activities a INNER JOIN coordinations c ON a.organized_by_id = c.id
                WHERE
                    a.state = 'notified' AND a.organized_by_id = :coordinationId
            ) result"""
        final sqlQuery = session.createSQLQuery(query)
        final result = sqlQuery.with {
            setLong('coordinationId', coordinationId)

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

    @Override
    Number countRequiringAttention() {
        final session = sessionFactory.currentSession
        final String query = """
           SELECT
               COUNT(*) total
           FROM
               (SELECT
                   a.name name, a.state state, c.name coord_name
               FROM
                   activities a
               INNER JOIN coordinations c ON a.organized_by_id = c.id
               WHERE
                   a.state = 'approved' UNION ALL SELECT
                   a.name name, a.state state, c.name coord_name
               FROM
                   activities a
               INNER JOIN coordinations c ON a.organized_by_id = c.id
               WHERE
                   a.state = 'notified'
                       AND c.area = 'Administrative') AS result"""
        final sqlQuery = session.createSQLQuery(query)
        final result = sqlQuery.with {
            uniqueResult()
        }

        result
    }
}
