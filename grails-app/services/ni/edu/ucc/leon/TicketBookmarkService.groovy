package ni.edu.ucc.leon

import grails.gorm.services.Service
import ni.edu.ucc.leon.Employee
import ni.edu.ucc.leon.Ticket

interface ITicketBookmarkService {

    TicketBookmark findByTicketAndEmployee(final Serializable ticketId, final Serializable employeeId)

    TicketBookmark toggle(final Serializable ticketId, final Serializable employeeId)

    TicketBookmark delete(final Serializable id)
}

@Service(TicketBookmark)
abstract class TicketBookmarkService implements ITicketBookmarkService {
    EmployeeService employeeService
    TicketService ticketService

    @Override
    TicketBookmark findByTicketAndEmployee(final Serializable ticketId, final Serializable employeeId) {
        TicketBookmark.where {
            ticket == Ticket.load(ticketId) && employee == Employee.load(employeeId)
        }.get()
    }

    @Override
    TicketBookmark toggle(final Serializable ticketId, final Serializable employeeId) {
        TicketBookmark ticketBookmark = findByTicketAndEmployee(ticketId, employeeId)

        if (!ticketBookmark) {
            TicketBookmark.create(employeeService.find(employeeId), ticketService.find(ticketId))
        } else {
           delete(ticketBookmark.id)
        }
    }
}
