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

    def show(Long id) {
        respond id ? ticketService.find(id) : null
    }

    def create(Long employeeId) {
        respond new Ticket(params), model: [ticketList: ticketService.listByEmployee(employeeId)]
    }

    def save(Long employeeId, String subject) {
        Ticket ticket = ticketService.save(employeeId, subject)

        if (ticket.hasErrors()) {
            respond ticket.errors, view: 'create', model: [ticketList: ticketService.listByEmployee(employeeId)]
            return
        } else {
            flash.message = 'Ticket creada'
            redirect uri: "/employees/$employeeId/tickets/$ticket.id", method: 'GET'
        }
    }

    def edit(Long employeeId, Long id) {
        if (!id) {
            notFound(employeeId)
        } else {
            respond ticketService.find(id), model: [ticketList: ticketService.listByEmployee(employeeId)]
        }
    }

    def update(Long employeeId, Long id, String subject) {
        try {
            Ticket ticket = ticketService.update(id, subject)

            if (!ticket) {
                notFound()
            } else {
                flash.message = 'Ticket actualizado'
                redirect uri: "/employees/$employeeId/tickets/$id", method: 'GET'
            }
        } catch(ValidationException e) {
            respond e.errors, view: 'edit'
        }
    }

    def delete(Long employeeId, Long id) {
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

    def assignment(TicketAssignmentCommand command) {
        if (command.hasErrors()) {
            flash.errors = command.errors
        } else {
            Ticket ticket = ticketService.assignment(command.id, command.device)

            flash.message = 'Asignado a ticket'
        }

        redirect uri: "/tickets/$command.id/tasks"
    }

    def swap(final Long id, final String status) {
        Ticket ticket = ticketService.swap(id, status)

        flash.message = !ticket ? 'Ticket no encontrada' : 'Ticket actualizada'

        redirect uri: "/tickets/$id/tasks", method: 'GET'
    }

    protected void notFound(Long employeeId) {
        flash.message = 'Ticket not found'
        redirect uri: "/employees/$employeeId/tickets", method: 'GET', status: NOT_FOUND
    }
}
