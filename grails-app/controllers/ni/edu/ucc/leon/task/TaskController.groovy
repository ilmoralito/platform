package ni.edu.ucc.leon.task

import static org.springframework.http.HttpStatus.NOT_FOUND
import grails.validation.ValidationException

import ni.edu.ucc.leon.TicketService
import ni.edu.ucc.leon.DeviceService
import ni.edu.ucc.leon.TaskService

import ni.edu.ucc.leon.Task

class TaskController {
    @Autowired TicketService ticketService
    @Autowired DeviceService deviceService
    @Autowired TaskService taskService

    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def index(Long ticketId) {
        respond taskService.listByTicket(ticketId), model: [
            ticket: ticketService.find(ticketId),
            deviceList: deviceService.list(),
            stateList: stateList()
        ]
    }

    def show(Long id, Long ticketId) {
        respond id ? taskService.find(id) : null
    }

    def edit(Long id, Long ticketId) {
        respond id ? taskService.find(id) : null
    }

    def update(Long id, Long ticketId, String description) {
        try {
            Task task = taskService.update(id, description)

            flash.message = 'Tarea actualizada'
            redirect uri: "/tickets/$ticketId/tasks/$id"
        } catch(ValidationException e) {
            respond e.errors, view: 'edit'
        }
    }

    def save(final String description, final Long ticketId) {
        try {
            Task task = taskService.save(description, ticketId)

            flash.message = 'Tarea agregada'
            redirect uri: "/tickets/$ticketId/tasks", method: 'GET'
        } catch(ValidationException e) {
            respond e.errors, view: 'index', model: [
                taskList: taskService.listByTicket(ticketId),
                ticket: ticketService.find(ticketId),
                deviceList: deviceService.list(),
                stateList: stateList()
            ]
        }
    }

    def delete(Long id, Long ticketId) {
        Task task = taskService.delete(id)

        if (!task) {
            notFound(ticketId)
        } else {
            flash.message = 'Tarea eliminada'
            redirect uri: "/tickets/$ticketId/tasks"
        }
    }

    def clone(final Long ticketId, final Long id) {
        try {
            Task task = taskService.clone(ticketId, id)

            flash.message = 'Tarea clonada'
            redirect uri: "/tickets/$ticketId/tasks"
        } catch(ValidationException e) {
            respond e.errors, view: 'index', model: [
                taskList: taskService.listByTicket(ticketId),
                ticket: ticketService.find(ticketId),
                deviceList: deviceService.list()
            ]
        }
    }

    def changeState(final Long ticketId, final Long id, final String state) {
        try {
            Task task = taskService.updateState(id, state)

            flash.message = 'Estado actualizado'
            redirect uri: "/tickets/$ticketId/tasks", method: 'GET'
        } catch(ValidationException e) {
            respond e.errors, view: 'index', model: [
                taskList: taskService.listByTicket(ticketId),
                ticket: ticketService.find(ticketId),
                deviceList: deviceService.list()
            ]
        }
    }

    protected notFound(Long ticketId) {
        flash.message = 'Tarea no encontrada'
        redirect uri: "/tickets/todo/$ticketId"
    }

    private List<Map<String, String>> stateList() {
        [[es: 'Predeterminado', en: 'default'], [es: 'Informacion', en: 'info'], [es: 'Advertencia', en: 'warning']]
    }
}
