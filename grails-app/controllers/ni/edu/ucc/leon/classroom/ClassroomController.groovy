package ni.edu.ucc.leon.classroom

import ni.edu.ucc.leon.Ticket
import org.springframework.beans.factory.annotation.Autowired

import static org.springframework.http.HttpStatus.NOT_FOUND
import grails.validation.ValidationException
import ni.edu.ucc.leon.ClassroomService
import ni.edu.ucc.leon.Classroom

class ClassroomController {

    @Autowired ClassroomService classroomService

    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def index() {
        respond classroomService.list()
    }

    def show(Long id) {
        respond id ? classroomService.find(id) : null
    }

    def create() {
        respond new Classroom(params)
    }

    def save(String code, String name, Integer capacity, Boolean wifi, Boolean airConditioned, Integer powerOutletNumber) {
        try {
            Classroom classroom = classroomService.save(code, name, capacity, wifi ?: false, airConditioned ?: false, powerOutletNumber)

            flash.message = 'Aula creada'
            redirect classroom
        } catch(ValidationException e) {
            respond e.errors, view: 'create'
        }
    }

    def edit(Long id) {
        respond id ? classroomService.find(id) : null
    }

    def update(Long id, String code, String name, Integer capacity, Boolean wifi, Boolean airConditioned, Integer powerOutletNumber) {
        try {
            Classroom classroom = classroomService.update(id, code, name, capacity, wifi ?: false, airConditioned ?: false, powerOutletNumber)

            if (!classroom) {
                notFound()
            } else {
                flash.message = 'Aula actualizada'
                redirect classroom
            }
        } catch(ValidationException e) {
            respond e.errors, view: 'edit'
        }
    }

    def delete(Long id) {
        Classroom classroom = classroomService.delete(id)

        if (!classroom) {
            notFound()
        } else {
            flash.message = 'Aula eliminada'
            redirect uri: '/classrooms', method: 'GET'
        }
    }

    protected notFound() {
        flash.message = 'Aula no encontrada'
        redirect uri: '/classrooms', method: 'GET'
    }
}
