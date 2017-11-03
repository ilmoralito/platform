package ni.edu.ucc.leon.ticket

import ni.edu.ucc.leon.TemporalTicketService
import ni.edu.ucc.leon.Ticket

class TicketInterceptor {

    TemporalTicketService temporalTicketService

    int order = HIGHEST_PRECEDENCE + 50

    TicketInterceptor() {
        match(controller: 'ticket' , action: ~/(update|delete)/)
    }

    boolean before() {
        Ticket ticket = temporalTicketService.find(params.id)

        if (ticket == null) {
            response.sendError 404
        }

        if (ticket) {
            if (ticket.status != 'open') {
                response.sendError 403
            }
        }

        true
    }

    boolean after() { true }

    void afterView() {}
}
