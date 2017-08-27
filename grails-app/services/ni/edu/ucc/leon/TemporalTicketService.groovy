package ni.edu.ucc.leon

import grails.transaction.Transactional

@Transactional
class TemporalTicketService {

    Ticket find(final Serializable id) {
        Ticket.get(id)
    }
}
