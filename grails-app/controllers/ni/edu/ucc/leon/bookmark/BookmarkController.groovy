package ni.edu.ucc.leon.bookmark

import grails.validation.ValidationException

import ni.edu.ucc.leon.TicketBookmarkService
import ni.edu.ucc.leon.TicketService

import ni.edu.ucc.leon.TicketBookmark

class BookmarkController {

    @Autowired TicketBookmarkService ticketBookmarkService
    @Autowired TicketService ticketService

    static allowedMethods = [save: 'POST', delete: 'DELETE']

    def index(Long employeeId) {
        respond ticketBookmarkService.listByEmployee(employeeId, [max: 100])
    }

    def save(Long employeeId, Long ticketId) {
        try {
            TicketBookmark ticketBookmark = ticketBookmarkService.save(employeeId, ticketId)

            flash.message = 'Ticket marcada'
            redirect uri: '/tickets', method: 'GET'
        } catch(ValidationException e) {
            respond e.errors, model: [ticketList: ticketService.findAllByStatusInList(['open', 'pending']), summaryStatus: ticketService.summaryStatus()]
        }
    }

    def delete(Long employeeId, Long id) {
        TicketBookmark ticketBookmark = ticketBookmarkService.delete(id)

        flash.message = 'Marcador eliminado'
        redirect uri: "/employees/$employeeId/bookmarks", permanent: true, method: 'GET'
    }
}
