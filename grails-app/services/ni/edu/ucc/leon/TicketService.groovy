package ni.edu.ucc.leon

import org.hibernate.transform.AliasToEntityMapResultTransformer
import org.hibernate.SessionFactory
import grails.gorm.services.Service
import grails.gorm.services.Where
import grails.gorm.services.Query

interface ITicketService {

    Ticket find(Serializable id)

    List<Ticket> findAllByStatusInList(List<String> status)

    List<Ticket> listByStatus(String status)

    List<Ticket> listByEmployee(Long employeeId)

    List<Map<String, String>> issuesPerEmployee(final Serializable employeeId)

    List<Ticket> listByStatusAndEmployee(String status, Serializable employeeId)

    List<Ticket> listByDevice(String name)

    Ticket save(Serializable employeeId, String subject)

    Ticket delete(Serializable id)

    Ticket update(Serializable id, String subject)

    Ticket assignment(final Serializable id, final Serializable employeeId, final Serializable deviceId)

    Ticket swap(final Serializable id, final String status)

    Map summaryStatus()

    Number countByStatusInList(List<String> statusList)

    @Query("SELECT DISTINCT new map (YEAR($t.dateCreated) AS year) FROM ${Ticket t}")
    List<Number> years()

    @Query("""
        SELECT
            new map (MONTHNAME(t.dateCreated) AS monthName, COUNT(*) AS count)
        FROM
            ${Ticket t}
        GROUP BY 1
        ORDER BY FIELD(1,
                'January',
                'February',
                'March',
                'April',
                'May',
                'June',
                'July',
                'August',
                'September',
                'October',
                'November',
                'December')
    """)
    List<Map<String, Object>> resume()

    @Query("""
        SELECT
            new map (MONTHNAME(t.dateCreated) AS monthName, COUNT(*) AS count)
        FROM
            ${Ticket t}
        WHERE
            YEAR(t.dateCreated) = $year
        GROUP BY 1
        ORDER BY FIELD(1,
                'January',
                'February',
                'March',
                'April',
                'May',
                'June',
                'July',
                'August',
                'September',
                'October',
                'November',
                'December')
    """)
    List<Map<String, Object>> resumeInYear(final Integer year)

    List<Map<String, Object>> resumeInMonth(final String monthName)

    List<Map<String, Object>> resumeInYearAndMonth(final Integer year, final String monthName)

    @Query("""
        SELECT
            new map ($d.name AS name, COUNT(*) AS count)
        FROM
            ${Ticket t}
                INNER JOIN
            ${Device d} ON t.device.id = d.id
        GROUP BY 1
        ORDER BY 2 DESC
    """)
    List<Map<String, String>> devices()

    @Query("""
        SELECT
            new map ($d.name AS name, COUNT(*) AS count)
        FROM
            ${Ticket t}
                INNER JOIN
            ${Device d} ON t.device.id = d.id
        WHERE
            YEAR(t.dateCreated) = $year
        GROUP BY 1
        ORDER BY 2 DESC
    """)
    List<Map<String, String>> devicesInYear(final Integer year)

    @Query("""
        SELECT
            new map (
                MONTHNAME(t.dateCreated) AS monthName,
                SUM(CASE WHEN t.status = 'open' THEN 1 ELSE 0 END) AS open,
                SUM(CASE WHEN t.status = 'pending' THEN 1 ELSE 0 END) AS pending,
                SUM(CASE WHEN t.status = 'closed' THEN 1 ELSE 0 END) AS closed,
                count(*) AS total
            )
        FROM
            ${Ticket t}
        GROUP BY 1
        ORDER BY FIELD(1,
                'January',
                'February',
                'March',
                'April',
                'May',
                'June',
                'July',
                'August',
                'September',
                'October',
                'November',
                'December')
    """)
    List<Map<String, Object>> state()

    @Query("""
        SELECT
            new map (
                MONTHNAME(t.dateCreated) AS monthName,
                SUM(CASE WHEN t.status = 'open' THEN 1 ELSE 0 END) AS open,
                SUM(CASE WHEN t.status = 'pending' THEN 1 ELSE 0 END) AS pending,
                SUM(CASE WHEN t.status = 'closed' THEN 1 ELSE 0 END) AS closed,
                count(*) AS total
            )
        FROM
            ${Ticket t}
        WHERE
            YEAR(t.dateCreated) = $year
        GROUP BY 1
        ORDER BY FIELD(1,
                'January',
                'February',
                'March',
                'April',
                'May',
                'June',
                'July',
                'August',
                'September',
                'October',
                'November',
                'December')
    """)
    List<Map<String, Object>> stateInYear(final Integer year)

    @Query("""
        SELECT
            new map (YEAR($t.dateCreated) AS year, COUNT(t.dateCreated) AS count)
        FROM
            ${Ticket t}
        GROUP BY 1
    """)
    List<Map<String, String>> yearList()

    List<Number> yearListByEmployee(Serializable employeeId)

    List<Map<String, Object>> employeeSummaryInYear(Serializable employeeId, Integer year)

    List<Map<String, Object>> employeeSummaryInYearAndMonth(Serializable employeeId, Integer year, Integer month)
}

@Service(Ticket)
abstract class TicketService implements ITicketService {

    @Autowired EmployeeService employeeService
    @Autowired SessionFactory sessionFactory
    @Autowired DeviceService deviceService

    @Override
    List<Ticket> listByStatusAndEmployee(String status, Serializable employeeId) {
        Ticket.where { status == 'open' && employee.id == employeeId }.list()
    }

    @Override
    Ticket save(Serializable employeeId, String subject) {
        Employee employee = employeeService.find(employeeId)
        Ticket ticket = new Ticket(subject: subject)

        List<Coordination> employeeCoordinationList = EmployeeCoordination.findAllByEmployee(employee).coordination

        employeeCoordinationList.each { coordination ->
            ticket.addToApplicantCoordinations(new ApplicantCoordination(coordination: coordination))
        }

        employee.addToTickets(ticket)

        ticket.save(flush: true)

        ticket
    }

    @Override
    Ticket assignment(final Serializable id, final Serializable employeeId, Serializable deviceId) {
        Employee employee = employeeService.find(employeeId)
        Device device = deviceService.find(deviceId)
        Ticket ticket = find(id)

        if (ticket && employee && device) {
            ticket.device = device
            ticket.status = 'pending'

            if (!AttendedBy.exists(employee, ticket)) {
                AttendedBy.create(employee, ticket)
            }

            ticket.save(flush: true)
        }

        ticket
    }

    @Override
    Ticket swap(final Serializable id, final String status) {
        Ticket ticket = find(id)

        if (ticket) {
            ticket.status = status
            ticket.save(flush: true)
        }

        ticket
    }

    @Override
    List<Number> yearListByEmployee(Serializable employeeId) {
        Ticket.executeQuery("""
            SELECT DISTINCT
                (YEAR(t.dateCreated)) AS year
            FROM
                Ticket t
                    INNER JOIN
                Employee e ON t.employee.id = e.id
            WHERE
                e.id = :employeeId
            ORDER BY year DESC
        """, [employeeId: employeeId])
    }

    @Override
    List<Map<String, Object>> employeeSummaryInYear(Serializable employeeId, Integer year) {
        Ticket.executeQuery("""
            SELECT new map(
                MONTHNAME(t.dateCreated) AS monthName,
                MONTH(t.dateCreated) AS month,
                COUNT(t.id) AS count
            )
            FROM
                Ticket t
                    INNER JOIN
                Employee e ON t.employee.id = e.id
            WHERE
                YEAR(t.dateCreated) = :year AND e.id = :employeeId
            GROUP BY 1 , 2
            ORDER BY monthName DESC
        """, [year: year, employeeId: employeeId])
    }

    List<Map<String, Object>> employeeSummaryInYearAndMonth(Serializable employeeId, Integer year, Integer month) {
        Ticket.executeQuery('''
            SELECT new map(
                t.id AS id,
                t.subject AS subject,
                DAYOFMONTH(t.dateCreated) AS dayOfMonth
            )
            FROM
                Ticket t
                    INNER JOIN
                Employee e ON t.employee.id = e.id
            WHERE
                YEAR(t.dateCreated) = :year
                    AND MONTH(t.dateCreated) = :month
                    AND e.id = :employeeId
        ''', [employeeId: employeeId, year: year, month: month])
    }

    @Override
    Map summaryStatus() {
        Ticket.executeQuery('''
            SELECT new map(
                SUM((CASE WHEN t.status = 'open' THEN 1 ELSE 0 END)) as open,
                SUM((CASE WHEN t.status = 'pending' THEN 1 ELSE 0 END)) as pending,
                SUM((CASE WHEN t.status = 'closed' THEN 1 ELSE 0 END)) as closed
            )
            FROM Ticket t
        ''')[0]
    }

    @Override
    List<Ticket> listByEmployee(Long employeeId) {
        Ticket.where {
            employee.id == employeeId
        }.list()
    }

    @Override
    List<Map<String, String>> issuesPerEmployee(final Serializable employeeId) {
        Ticket.executeQuery("""
            SELECT
                DISTINCT new map (t.subject AS subject)
            FROM
                Ticket t
            WHERE
                t.employee.id = :employeeId
            """, [employeeId: employeeId])
    }

    @Override
    List<Ticket> listByDevice(String name) {
        Ticket.where {
            device.name == name
        }.list()
    }

    @Override
    List<Map<String, Object>> resumeInMonth(final String monthName) {
        final session = sessionFactory.currentSession
        final String query = """
            SELECT
                e.full_name AS fullName,
                t.subject AS issue,
                CASE t.status
                    WHEN 'open' THEN 'open'
                    WHEN 'pending' THEN 'In progress'
                    WHEN 'closed' THEN 'closed'
                END AS status,
                CASE t.scheduled
                    WHEN TRUE THEN 'scheduled'
                    WHEN FALSE THEN 'non-scheduled'
                END AS scheduled,
                ifnull(d.name, '') AS device,
                DATE(t.date_created) AS dateCreated,
                DATE(t.last_updated) AS lastUpdated,
                IFNULL(total_tasks, 0) AS tasks
            FROM
                tickets t
                    INNER JOIN
                employees e ON t.employee_id = e.id
                    LEFT JOIN
                devices d ON d.id = t.device_id
                    LEFT JOIN
                (SELECT
                    ticket_id, COUNT(1) AS total_tasks
                FROM
                    tasks
                GROUP BY ticket_id) ta ON t.id = ta.ticket_id
            WHERE
                MONTHNAME(t.date_created) = :monthName
            ORDER BY dateCreated DESC
        """
        final sqlQuery = session.createSQLQuery(query)
        final results = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            setString('monthName', monthName)

            list()
        }

        results
    }

    @Override
    List<Map<String, Object>> resumeInYearAndMonth(final Integer year, final String monthName) {
        final session = sessionFactory.currentSession
        final String query = """
            SELECT
                e.full_name AS fullName,
                t.subject AS issue,
                CASE t.status
                    WHEN 'open' THEN 'open'
                    WHEN 'pending' THEN 'In progress'
                    WHEN 'closed' THEN 'closed'
                END AS status,
                CASE t.scheduled
                    WHEN TRUE THEN 'scheduled'
                    WHEN FALSE THEN 'non-scheduled'
                END AS scheduled,
                ifnull(d.name, '') AS device,
                DATE(t.date_created) AS dateCreated,
                DATE(t.last_updated) AS lastUpdated,
                IFNULL(total_tasks, 0) AS tasks
            FROM
                tickets t
                    INNER JOIN
                employees e ON t.employee_id = e.id
                    LEFT JOIN
                devices d ON d.id = t.device_id
                    LEFT JOIN
                (SELECT
                    ticket_id, COUNT(1) AS total_tasks
                FROM
                    tasks
                GROUP BY ticket_id) ta ON t.id = ta.ticket_id
            WHERE
                MONTHNAME(t.date_created) = :monthName
                    AND YEAR(t.date_created) = :year
            ORDER BY dateCreated DESC
        """
        final sqlQuery = session.createSQLQuery(query)
        final results = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            setString('monthName', monthName)
            setInteger('year', year)

            list()
        }

        results
    }
}
