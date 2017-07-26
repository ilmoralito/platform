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

    static allowedMethods = [ save: 'POST', update: 'PUT', delete: 'DELETE', clone: 'POST', updateState: 'PUT' ]

    def index(Long ticketId) {
        respond taskService.listByTicket(ticketId), model: [ticket: ticketService.find(ticketId), deviceList: deviceService.list()]
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

    def save(TaskSaveCommand command) {
        if (command.hasErrors()) {
            flash.errors = command.errors
            flash.message = 'A ocurrido un error'

            redirect uri: "/tickets/${command.ticketId}/tasks", method: 'GET'
        } else {
            Task task = taskService.save(command.description, command.ticketId)

            flash.message = 'Tarea guardada'
            redirect uri: "/tickets/$command.ticketId/tasks/$task.id", method: 'GET'
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

    def clone(Long ticketId, Long id) {
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

    def updateState(Long ticketId, Long id, String state) {
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
}
