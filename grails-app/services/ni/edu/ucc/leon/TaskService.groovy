package ni.edu.ucc.leon

import grails.plugin.springsecurity.SpringSecurityService
import grails.gorm.services.Service

interface ITaskService {

    List<Task> listByTicket(Serializable ticketId)

    Task find(Serializable id)

    Task save(final String description, final Serializable ticketId)

    Task update(Serializable id, String description)

    Task delete(Serializable id)

    Task clone(Serializable ticketId, Serializable taskId)

    Task updateState(Serializable id, String state)
}

@Service(Task)
abstract class TaskService implements ITaskService {
    @Autowired SpringSecurityService springSecurityService
    TicketService ticketService

    @Override
    List<Task> listByTicket(Serializable ticketId) {
        Task.where {
            ticket.id == ticketId
        }.list()
    }

    @Override
    Task save(final String description, final Serializable ticketId) {
        User user = springSecurityService.currentUser
        Task task = new Task(createdBy: user, description: description)
        Ticket ticket = ticketService.find(ticketId)

        ticket.addToTasks(task)

        task.save(failOnError: true)
    }

    @Override
    Task clone(Serializable ticketId, Serializable taskId) {
        Task task = find(taskId)
        Task newTask

        if (task) {
            newTask = save(task.description, ticketId)
        }

        newTask
    }

    @Override
    Task updateState(Serializable id, String state) {
        Task task = find(id)

        if (task) {
            task.state = state

            task.save(flush: true)
        }

        task
    }
}
