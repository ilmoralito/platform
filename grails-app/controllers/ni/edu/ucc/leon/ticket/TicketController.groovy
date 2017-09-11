package ni.edu.ucc.leon.ticket

import static org.springframework.http.HttpStatus.NOT_FOUND
import grails.plugin.springsecurity.SpringSecurityService
import grails.validation.ValidationException
import ni.edu.ucc.leon.EmployeeService
import ni.edu.ucc.leon.TicketService
import ni.edu.ucc.leon.DeviceService
import ni.edu.ucc.leon.Ticket
import ni.edu.ucc.leon.Helper

class TicketController {
    @Autowired SpringSecurityService springSecurityService
    @Autowired EmployeeService employeeService
    @Autowired TicketService ticketService
    @Autowired DeviceService deviceService

    static allowedMethods = [ save: 'POST', update: 'PUT', delete: 'DELETE', assignment: 'POST', swap: 'POST' ]

    def index(final Long employeeId) {
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

    def summaryInYear(final Long employeeId, final Integer year) {
        [summaryList: ticketService.employeeSummaryInYear(employeeId, year)]
    }

    def summaryInYearAndMonth(final Long employeeId, final Integer year, final Integer month) {
        [ticketList: ticketService.employeeSummaryInYearAndMonth(employeeId, year, month)]
    }

    def tickets() {
        respond ticketService.findAllByStatusInList(['open', 'pending']), model: [summaryStatus: ticketService.summaryStatus()]
    }

    def filter() {
        [
            employeeList: employeeService.list(),
            deviceList: deviceService.list(),
            statesList: Helper.statesList,
        ]
    }

    def applyFilter() {
        final List<String> statesList = params.list('states')
        final List<String> deviceList = params.list('devices')
        final List<Long> employeeList = params.list('employees')

        List<Ticket> ticketList = Ticket.createCriteria().list {
            if (statesList) {
                'in' 'status', statesList
            }

            if (deviceList) {
                device {
                    'in' 'name', deviceList
                }
            }

            if (employeeList) {
                employee {
                    'in' 'id', employeeList*.toLong()
                }
            }
        }

        render model: [ticketList: ticketList, summaryStatus: ticketService.summaryStatus()], view: 'tickets'
    }

    def filterByStatus(final String status) {
        respond ticketService.listByStatus(status), model: [summaryStatus: ticketService.summaryStatus()], view: 'tickets'
    }

    def filterByEmployee(final Long employeeId) {
        respond ticketService.listByEmployee(employeeId), model: [summaryStatus: ticketService.summaryStatus()], view: 'tickets'
    }

    def filterByDevice(final String name) {
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

    protected void notFound(final Long employeeId) {
        flash.message = 'Ticket not found'
        redirect uri: "/employees/$employeeId/tickets", method: 'GET', status: NOT_FOUND
    }
}
