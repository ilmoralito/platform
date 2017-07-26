package ni.edu.ucc.leon

class AttendedBy {
    Employee employee
    Ticket ticket

    Date dateCreated

    static constraints = {
        employee nullable: false
        ticket nullable: false
    }

    static mapping = {
        version false
        sort dateCreated: 'desc'
    }

    static AttendedBy create(Employee employee, Ticket ticket) {
        new AttendedBy(employee: employee, ticket: ticket).save(flush: true)
    }

    static Boolean exists(Employee employee, Ticket ticket) {
        AttendedBy.where {
            employee == employee && ticket == ticket
        }.count()
    }

    static List<Employee> listEmployeesByTicket(Ticket ticket) {
        AttendedBy.where {
            ticket == ticket
        }.list().employee
    }
}
