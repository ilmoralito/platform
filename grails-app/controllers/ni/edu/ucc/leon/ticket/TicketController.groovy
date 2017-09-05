package ni.edu.ucc.leon.ticket

import static org.springframework.http.HttpStatus.NOT_FOUND
import grails.plugin.springsecurity.SpringSecurityService
import grails.validation.ValidationException
import ni.edu.ucc.leon.TicketService
import ni.edu.ucc.leon.Ticket

class TicketController {
    SpringSecurityService springSecurityService
    TicketService ticketService

    static allowedMethods = [
        save: 'POST',
        update: 'PUT',
        delete: 'DELETE',
        assignment: 'POST',
        swap: 'POST'
    ]

    def index(Long employeeId) {
        respond ticketService.listByStatusAndEmployee('open', employeeId)
    }

    def show(final Long id) {
        respond id ? ticketService.find(id) : null
    }

    def create(final Long employeeId) {
        respond new Ticket(params), model: [ticketIssuesList: ticketService.issuesPerEmployee(employeeId)]
    }

    def save(final Long employeeId, final String subject) {
        Ticket ticket = ticketService.save(employeeId, subject)

        if (ticket.hasErrors()) {
            respond ticket.errors, view: 'create', model: [ticketIssuesList: ticketService.issuesPerEmployee(employeeId)]
            return
        } else {
            flash.message = 'Ticket creada'
            redirect uri: "/employees/$employeeId/tickets/$ticket.id", method: 'GET'
        }
    }

    def edit(final Long employeeId, final Long id) {
        if (!id) {
            notFound(employeeId)
            return
        }
        
        respond ticketService.find(id), model: [ticketIssuesList: ticketService.issuesPerEmployee(employeeId)]
    }

    def update(final Long employeeId, final Long id, final String subject) {
        try {
            Ticket ticket = ticketService.update(id, subject)

            if (!ticket) {
                notFound()
            } else {
                flash.message = 'Ticket actualizado'
                redirect uri: "/employees/$employeeId/tickets/$id", method: 'GET'
            }
        } catch(ValidationException e) {
            respond e.errors, view: 'edit', model: [ticketIssuesList: ticketService.issuesPerEmployee(employeeId)]
        }
    }

    def delete(final Long employeeId, final Long id) {
        Ticket ticket = ticketService.delete(id)

        if (ticket == null) {
            notFound()
        } else {
            flash.message = 'Tikcet eliminada'
            redirect uri: "/employees/$employeeId/tickets", method: 'GET'
        }
    }

    def summary(Long employeeId) {
        [yearList: ticketService.yearListByEmployee(employeeId)]
    }

    def summaryInYear(Long employeeId, Integer year) {
        [summaryList: ticketService.employeeSummaryInYear(employeeId, year)]
    }

    def summaryInYearAndMonth(Long employeeId, Integer year, Integer month) {
        [ticketList: ticketService.employeeSummaryInYearAndMonth(employeeId, year, month)]
    }

    def tickets() {
        respond ticketService.findAllByStatusInList(['open', 'pending']), model: [summaryStatus: ticketService.summaryStatus()]
    }

    def filter(String status) {
        respond ticketService.listByStatus(status), model: [summaryStatus: ticketService.summaryStatus()], view: 'tickets'
    }

    def filterByEmployee(Long employeeId) {
        respond ticketService.listByEmployee(employeeId), model: [summaryStatus: ticketService.summaryStatus()], view: 'tickets'
    }

    def filterByDevice(String name) {
        respond ticketService.listByDevice(name), model: [summaryStatus: ticketService.summaryStatus()], view: 'tickets'
    }

    def assignment(final Long id, final Long deviceId) {
        Ticket ticket = ticketService.assignment(id, springSecurityService.currentUser.employee.id, deviceId)

        flash.message = !ticket ? 'Ticket no encontrada' : 'Ticket asignada'
        redirect uri: "/tickets/$id/tasks", method: 'GET'
    }

    def swap(final Long id, final String status) {
        Ticket ticket = ticketService.swap(id, status)

        flash.message = !ticket ? 'Ticket no encontrada' : 'Ticket actualizada'

        redirect uri: "/tickets/$id/tasks", method: 'GET'
    }

    def resume(final Long id) {
        respond ticketService.find(id)
    }

    protected void notFound(Long employeeId) {
        flash.message = 'Ticket not found'
        redirect uri: "/employees/$employeeId/tickets", method: 'GET', status: NOT_FOUND
    }
}
