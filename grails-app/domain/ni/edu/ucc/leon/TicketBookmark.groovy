package ni.edu.ucc.leon

class TicketBookmark extends Bookmark {

    Ticket ticket

    static constraints = {
        ticket nullable: false
    }

    static TicketBookmark create(Employee employee, Ticket ticket) {
        new TicketBookmark(employee: employee, ticket: ticket).save(flush: true)
    }
}
