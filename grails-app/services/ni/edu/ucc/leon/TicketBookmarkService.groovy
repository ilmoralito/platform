package ni.edu.ucc.leon

import grails.gorm.services.Service

interface ITicketBookmarkService {

    List<TicketBookmark> listByEmployee(Serializable employeeId, Map args)

    TicketBookmark save(Serializable employeeId, Serializable ticketId)

    TicketBookmark delete(Serializable id)

    Number countByEmployeeAndTicket(Serializable employeeId, Serializable ticketId)

    Number countByEmployee(Serializable employeeId)
}

@Service(TicketBookmark)
abstract class TicketBookmarkService implements ITicketBookmarkService {
    EmployeeService employeeService
    TicketService ticketService

    @Override
    List<TicketBookmark> listByEmployee(Serializable employeeId, Map args) {
        Employee employee = employeeService.find(employeeId)

        TicketBookmark.where { employee == employee }.list(args)
    }

    @Override
    TicketBookmark save(Serializable employeeId, Serializable ticketId) {
        Employee employee = employeeService.find(employeeId)
        Ticket ticket = ticketService.find(ticketId)

        if (!employee || !ticket) {
            throw new Exception('Not needed parameters')
        }

        if (!countByEmployeeAndTicket(employeeId, ticketId)) {
            TicketBookmark.create(employee, ticket)
        }
    }

    @Override
    Number countByEmployeeAndTicket(Serializable employeeId, Serializable ticketId) {
        Employee employee = employeeService.find(employeeId)
        Ticket ticket = ticketService.find(ticketId)

        TicketBookmark.where {
            employee == employee && ticket == ticket
        }.count()
    }

    @Override
    Number countByEmployee(Serializable employeeId) {
        Employee employee = employeeService.find(employeeId)

        TicketBookmark.where {
            employee == employee
        }.count()
    }
}
