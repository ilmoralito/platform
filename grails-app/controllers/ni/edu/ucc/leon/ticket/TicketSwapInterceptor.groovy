package ni.edu.ucc.leon.ticket

import ni.edu.ucc.leon.TemporalTicketService
import ni.edu.ucc.leon.Ticket

class TicketSwapInterceptor {

    TemporalTicketService temporalTicketService

    TicketSwapInterceptor() {
        match(controller: 'ticket' , action: 'swap')
    }

    boolean before() {
        Ticket ticket = temporalTicketService.find(params.id)

        if (ticket.status == 'open' || ticket.tasks.size() == 0) {
            response.sendError 403
        }

        true
    }

    boolean after() { true }

    void afterView() {}
}
