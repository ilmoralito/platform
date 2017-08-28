package ni.edu.ucc.leon

import grails.plugin.springsecurity.SpringSecurityService
import static java.util.Calendar.*
import ni.edu.ucc.leon.Helper
import groovy.xml.*

class PlatformTagLib {
    SpringSecurityService springSecurityService
    @Autowired TicketBookmarkService ticketBookmarkService
    @Autowired BirthdayService birthdayService
    @Autowired TicketService ticketService

    static defaultEncodeAs = [taglib: 'none']

    static namespace = 'platform'

    def ticketStatus = { attrs ->
        switch(attrs.status) {
            case 'open':
                out << 'ABIERTO'
            break
            case 'pending':
                out << 'EN PROGRESO'
            break
            case 'closed':
                out << 'CERRADO'
            break
        }
    }

    def coordinationAreas = { attrs ->
        switch(attrs.area) {
            case 'academic':
                out << 'Academico'
            break
            case 'administrative':
                out << 'Administrativo'
            break
        }
    }

    def yesNo = { attrs ->
        if (attrs.condition) {
            out << 'Si'
        } else {
            out << 'No'
        }
    }

    def ticketsToAttend = { attrs ->
        out << ticketService.countByStatusInList(['open', 'pending'])
    }

    def enabled = { attrs ->
        if (attrs.enabled) {
            out << 'Habilitado'
        } else {
            out << 'No habilitado'
        }
    }

    def position = { attrs ->
        if (attrs.position == 'manager') {
            out << 'Coordinador'
        } else {
            out << 'Colaborador'
        }
    }

    def bookmarkListCount = { attrs, body ->
        Long employeeId = springSecurityService.currentUser.employee.id
        Number count = ticketBookmarkService.countByEmployee(employeeId)

        if (count) {
            out << body()
        }
    }

    def bookmarkList = {
        Long employeeId = springSecurityService.currentUser.employee.id
        List<Bookmark> bookmarkList = ticketBookmarkService.listByEmployee(employeeId, [max: 5])

        for(Bookmark bookmark in bookmarkList) {
            out << """
                <li>
                    <a href='/tickets/$bookmark.ticket.id/tasks'>${bookmark.ticket.subject}</a>
                </li>
            """
        }
    }

    def areThereAnyBirthdayPartiesToday = { attrs, body ->
        if (birthdayService.listBirthdayOfTheDay()) {
            out << body()
        }
    }

    def typeOfContract = { attrs ->
        if (attrs.type == 'permanent') {
            out << 'Fijo'
        } else {
            out << 'Horario'
        }
    }

    def attendedByEmployeeList = { attrs ->
        Ticket ticket = attrs.ticket
        MarkupBuilder builder = new MarkupBuilder(out)
        List<Employee> employeeList = AttendedBy.listEmployeesByTicket(ticket)

        if (employeeList) {
            builder.div(style: 'margin-top: 10px;') {
                label 'Asignado a'

                ol {
                    for(Employee employee in employeeList) {
                        li employee.fullName
                    }
                }
            }
        }
    }

    def attendedByEmployeeFullNameList = { attrs ->
        Ticket ticket = attrs.ticket
        MarkupBuilder builder = new MarkupBuilder(out)

        out << AttendedBy.listEmployeesByTicket(ticket).fullName.join(', ')
    }

    def dayOfMonth = { attrs ->
        out << Helper.months[new Date()[Calendar.MONTH]]
    }
}
