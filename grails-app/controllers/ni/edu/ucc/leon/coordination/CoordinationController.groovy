package ni.edu.ucc.leon.coordination

import static org.springframework.http.HttpStatus.NOT_FOUND
import grails.validation.ValidationException
import ni.edu.ucc.leon.CoordinationService
import ni.edu.ucc.leon.Coordination

class CoordinationController {
    CoordinationService coordinationService

    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def index() {
        List<Coordination> coordinationList = coordinationService.list()

        respond coordinationList
    }

    def create() {
        respond new Coordination(params)
    }

    def save(String name, String acronym, String extensionNumber, Integer copyFee, String area) {
        try {
            Coordination coordination = coordinationService.save(name, acronym, extensionNumber, copyFee, area)

            flash.message = 'Coordinacion guardada'
            redirect coordination
        } catch(ValidationException e) {
            respond e.errors, view: 'create'
        }
    }

    def show(Long id) {
        Coordination coordination = id ? coordinationService.find(id) : null

        respond coordination
    }

    def edit(Long id) {
        Coordination coordination = id ? coordinationService.find(id) : null

        respond coordination
    }

    def update(Long id, String name, String acronym, String extensionNumber, Integer copyFee, String area) {
        try {
            Coordination coordination = coordinationService.update(id, name, acronym, extensionNumber, copyFee, area)

            if (!coordination) {
                notFound()
            } else {
                flash.message = 'Coordinacion actualizada'
                redirect coordination
            }
        } catch (ValidationException e) {
            respond e.errors, view: 'edit'
        }
    }

    def delete(Long id) {
        Coordination coordination = coordinationService.delete(id)

        if (!coordination) {
            notFound()
        } else {
            flash.message = 'Coordinacion eliminada'
            redirect uri: '/coordinations', method: 'GET'
        }
    }

    protected notFound() {
        flash.message = 'Coordinacion no encontrada'
        redirect uri: '/coordinations', method: 'GET'
    }
}
