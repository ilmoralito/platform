package ni.edu.ucc.leon.ticket

import ni.edu.ucc.leon.TemporalTicketService
import ni.edu.ucc.leon.Ticket

class TicketAssignmentInterceptor {

    TemporalTicketService temporalTicketService

    TicketAssignmentInterceptor() {
        match(controller: 'ticket' , action: 'assignment')
    }

    boolean before() {
        Ticket ticket = temporalTicketService.find(params.id)

        if (ticket.status == 'closed') {
            response.sendError 403
        }

        true
    }

    boolean after() { true }

    void afterView() {}
}
