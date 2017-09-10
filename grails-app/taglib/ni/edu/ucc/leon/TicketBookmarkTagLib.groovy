package ni.edu.ucc.leon

import ni.edu.ucc.leon.TicketBookmarkService
import groovy.xml.*

class TicketBookmarkTagLib {

    @Autowired TicketBookmarkService ticketBookmarkService

    static defaultEncodeAs = [taglib: 'none']

    static namespace = 'ticketBookmark'

    def bookmark = { attrs ->
        final Long ticketId = attrs.ticketId
        final Long employeeId = attrs.employeeId
        final String controllerName = attrs.controllerName
        MarkupBuilder markupBuilder = new MarkupBuilder(out)
        TicketBookmark ticketBookmark = ticketBookmarkService.findByTicketAndEmployee(ticketId, employeeId)

        markupBuilder.a(href: '#', 'class': 'toggleTicketBookmark', 'data-ticket-id': ticketId, 'data-employee-id': employeeId, 'data-controller-name': controllerName) {
            if (ticketBookmark) {
                span(id: "glyphicon-bookmark-$ticketId", 'class': 'glyphicon glyphicon-bookmark')
            } else {
                span(id: "glyphicon-bookmark-$ticketId", 'class': 'glyphicon glyphicon-bookmark', style: 'color: #DDD;')
            }

        }
    }
}
