package ni.edu.ucc.leon.ticketBookmark

import grails.plugin.springsecurity.SpringSecurityService
import ni.edu.ucc.leon.TicketBookmarkService
import ni.edu.ucc.leon.TicketBookmark
import ni.edu.ucc.leon.TicketService
import ni.edu.ucc.leon.Ticket

class TicketBookmarkController {

    @Autowired TicketBookmarkService ticketBookmarkService
    @Autowired SpringSecurityService springSecurityService
    @Autowired TicketService ticketService

    static allowedMethods = [ toggle: 'POST' ]

    def index() {
        List<Long> ids = TicketBookmark.findAllByEmployee(springSecurityService.currentUser.employee).ticket.id

        render model: [summaryStatus: ticketService.summaryStatus(), ticketList: Ticket.getAll(ids)], view: '/ticket/tickets'
    }

    def toggle(final Long ticketId, final Long employeeId) {
        respond ticketBookmarkService.toggle(ticketId, employeeId), formats: ['json']
    }
}
