package ni.edu.ucc.leon

import org.hibernate.transform.AliasToEntityMapResultTransformer
import grails.plugin.springsecurity.SpringSecurityService
import grails.gorm.services.Service
import grails.gorm.services.Where
import grails.gorm.services.Query

interface ITicketService {

    Ticket find(Serializable id)

    List<Ticket> findAllByStatusInList(List<String> status)

    List<Ticket> listByStatus(String status)

    List<Ticket> listByEmployee(Long employeeId)

    List<Ticket> listByStatusAndEmployee(String status, Serializable employeeId)

    List<Ticket> listByDevice(String name)

    Ticket save(Serializable employeeId, String subject)

    Ticket delete(Serializable id)

    Ticket update(Serializable id, String subject)

    Ticket assignment(Serializable id, Serializable deviceId)

    Ticket swap(final Serializable id, final String status)

    Map summaryStatus()

    Number countByStatusInList(List<String> statusList)

    @Query("""
        SELECT 
            new map (YEAR($t.dateCreated) AS year, COUNT(t.dateCreated) AS count)
        FROM
            ${Ticket t}
        GROUP BY 1
    """)
    List<Number> yearList()

    @Query("""
        SELECT new map (
            MONTHNAME(t.dateCreated) AS monthName,
            MONTH(t.dateCreated) AS month,
            COUNT(t.dateCreated) AS count
        )
        FROM
            ${Ticket t}
        WHERE
            YEAR($t.dateCreated) = $year
        GROUP BY 1 , 2
        ORDER BY 2 DESC
    """)
    List<Map<String, Object>> monthList(final Integer year)

    List<Map<String, Object>> statusInYear(final Integer year)

    List<Map<String, Object>> statusInYearAndMonth(final Integer year, final Integer month)

    @Query("""
        SELECT 
            new map (d.name AS name, COUNT(*) AS count)
        FROM
            ${Ticket t}
                INNER JOIN
            ${Device d} ON t.device.id = d.id
        WHERE
            YEAR($t.dateCreated) = $year
        GROUP BY 1 order by 2 desc
    """)
    List<Map<String, Object>> devicesInYear(final Integer year)

    @Query("""
        SELECT 
            new map (d.name AS name, COUNT(*) AS count)
        FROM
            ${Ticket t}
                INNER JOIN
            ${Device d} ON t.device.id = d.id
        WHERE
            YEAR($t.dateCreated) = $year
                AND MONTH($t.dateCreated) = $month
        GROUP BY 1 order by 2 desc
    """)
    List<Map<String, Object>> devicesInYearAndMonth(final Integer year, final Integer month)

    List<Number> yearListByEmployee(Serializable employeeId)

    List<Map<String, Object>> employeeSummaryInYear(Serializable employeeId, Integer year)

    List<Map<String, Object>> employeeSummaryInYearAndMonth(Serializable employeeId, Integer year, Integer month)
}

@Service(Ticket)
abstract class TicketService implements ITicketService {

    @Autowired SpringSecurityService springSecurityService
    @Autowired EmployeeService employeeService
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
    Ticket assignment(Serializable id, Serializable deviceId) {
        Employee employee = springSecurityService.currentUser.employee
        Ticket ticket = find(id)

        if (ticket) {
            Device device = deviceService.find(deviceId)

            if (!device) {
                throw new IllegalArgumentException('Device does not exist')
            }

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
    List<Map<String, Object>> statusInYear(Integer year) {
        Ticket.executeQuery('''
            SELECT new map (
                c.name as coordination,
                SUM((CASE WHEN t.status = 'open' THEN 1 ELSE 0 END)) as open,
                SUM((CASE WHEN t.status = 'pending' THEN 1 ELSE 0 END)) as pending,
                SUM((CASE WHEN t.status = 'closed' THEN 1 ELSE 0 END)) as closed
            )
            FROM
                Ticket t, EmployeeCoordination ec
                    JOIN t.employee e JOIN ec.coordination c
            WHERE
                YEAR(t.dateCreated) = :year
                    AND ec.employee.id = e.id AND ec.coordination.id = c.id
            GROUP BY 1
        ''', [year: year])
    }

    @Override
    List<Map<String, Object>> statusInYearAndMonth(Integer year, Integer month) {
        Ticket.executeQuery('''
            SELECT new map (
                c.name as coordination,
                SUM((CASE WHEN t.status = 'open' THEN 1 ELSE 0 END)) as open,
                SUM((CASE WHEN t.status = 'pending' THEN 1 ELSE 0 END)) as pending,
                SUM((CASE WHEN t.status = 'closed' THEN 1 ELSE 0 END)) as closed
            )
            FROM
                Ticket t, EmployeeCoordination ec
                    JOIN t.employee e JOIN ec.coordination c
            WHERE
                YEAR(t.dateCreated) = :year
                    AND MONTH(t.dateCreated) = :month
                        AND ec.employee.id = e.id AND ec.coordination.id = c.id
            GROUP BY 1
        ''', [year: year, month: month])
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
    List<Ticket> listByDevice(String name) {
        Ticket.where {
            device.name == name
        }.list()
    }
}
