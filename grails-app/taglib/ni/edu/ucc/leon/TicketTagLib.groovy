package ni.edu.ucc.leon

import groovy.xml.*

class TicketTagLib {
    static defaultEncodeAs = [taglib: 'none']

    static namespace = 'ticket'

    def filterLink = { attrs ->
        String status = attrs.status
        Integer quantity = attrs.quantity
        MarkupBuilder markupBuilder = new MarkupBuilder(out)

        if (quantity) {
            markupBuilder.a(href: "/tickets/status/$status") {
                mkp.yield quantity
            }
        } else {
            out << quantity
        }
    }

    def filterButton = { attrs ->
        String status = attrs.status
        Integer quantity = attrs.quantity
        MarkupBuilder markupBuilder = new MarkupBuilder(out)

        markupBuilder.a(href: "/tickets/status/$status", class: "btn btn-default ${isActive(status)}") {
            mkp.yield "${getStatusInSpanish(status)} $quantity"
        }
    }

    private String isActive(final String status) {
        List<String> actionNames = ['filterByEmployee', 'filterByDevice', 'applyFilter']

        if (status == 'open') {
            if (params.status == 'open' || !params.status && controllerName != 'ticketBookmark' && !(actionName in actionNames)) {
                return 'active'
            }
        } else if (status == 'pending') {
            if (params.status == 'pending' || !params.status && controllerName != 'ticketBookmark' && !(actionName in actionNames)) {
                return 'active'
            }
        } else if (status == 'closed' && params.status == 'closed') {
            return 'active'
        }
    }

    private String getStatusInSpanish(final String status) {
        if (status == 'open') {
            return 'Abierto'
        } else if (status == 'pending') {
            return 'En progreso'
        } else {
            return 'Cerrado'
        }
    }
}
