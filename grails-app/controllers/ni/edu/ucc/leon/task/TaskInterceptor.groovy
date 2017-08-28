package ni.edu.ucc.leon.task

import ni.edu.ucc.leon.TemporalTicketService
import ni.edu.ucc.leon.Ticket

class TaskInterceptor {

    TemporalTicketService temporalTicketService

    TaskInterceptor() {
        match(controller: 'task', action: ~/(update|save|delete)/)
    }

    boolean before() {
        Ticket ticket = temporalTicketService.find(params.ticketId)

        if (ticket.status == 'closed') {
            response.sendError 403
        }

        true
    }

    boolean after() { true }

    void afterView() {}
}
