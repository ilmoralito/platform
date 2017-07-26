package ni.edu.ucc.leon.career

import static org.springframework.http.HttpStatus.NOT_FOUND
import grails.validation.ValidationException
import ni.edu.ucc.leon.CareerService
import ni.edu.ucc.leon.Career

class CareerController {
    @Autowired CareerService careerService

    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def index() {
        List<Career> careerList = careerService.list()

        respond careerList
    }

    def show(Long id) {
        respond id ? careerService.find(id) : null
    }

    def create() {
        respond new Career(params)
    }

    def save(String name) {
        try {
            Career career = careerService.save(name)

            flash.message = 'Carrera guardada'
            redirect career
        } catch(ValidationException e) {
            respond e.errors, view: 'create'
        }
    }

    def edit(Long id) {
        respond id ? careerService.find(id) : null
    }

    def update(Long id, String name) {
        try {
            Career career = careerService.update(id, name)

            if (!career) {
                notFound()
            } else {
                flash.message = 'Coordinacion actualizada'
                redirect career
            }
        } catch (ValidationException e) {
            respond e.errors, view: 'edit'
        }
    }

    def delete(Long id) {
        Career career = careerService.delete(id)

        if (!career) {
            notFound()
        } else {
            flash.message = 'Carrera eliminada'
            redirect uri: '/careers', method: 'GET'
        }
    }

    protected notfound() {
        flash.message = 'Carrera no encontrada'
        redirect uri: '/careers', method: 'GET'
    }
}
