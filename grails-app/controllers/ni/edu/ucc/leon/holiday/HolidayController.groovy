package ni.edu.ucc.leon.holiday

import static org.springframework.http.HttpStatus.NOT_FOUND
import grails.validation.ValidationException
import groovy.transform.CompileStatic
import ni.edu.ucc.leon.HolidayService
import ni.edu.ucc.leon.Holiday

// @CompileStatic
class HolidayController {

    @Autowired HolidayService holidayService

    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def index() {
        respond holidayService.list()
    }

    def create() {
        respond new Holiday(params)
    }

    def save() {
        try {
            Holiday holiday = holidayService.save(params.date, params.name, params.wiki)

            flash.message = 'Dia feriado creado'
            redirect holiday
        } catch(ValidationException e) {
            respond e.errors, view: 'create'
        }
    }

    def show(final Long id) {
        respond id ? holidayService.find(id) : null
    }

    def edit(final Long id) {
        respond id ? holidayService.find(id) : null
    }

    def update(final Long id) {
        try {
            Holiday holiday = holidayService.update(id, params.date, params.name, params.wiki)

            if (!holiday) {
                notFound()
            } else {
                flash.message = 'Dia feriado actualizado'
                redirect holiday
            }
        } catch(ValidationException e) {
            respond e.errors, view: 'edit'
        }
    }

    def delete(final Long id) {
        Holiday holiday = holidayService.delete(id)

        if (!holiday) {
            notFound()
        } else {
            flash.message = 'Dia feriado eliminado'
            redirect uri: '/holidays', method: 'GET'
        }
    }

    protected void notFound() {
        flash.message = 'Holiday no encontrado'
        redirect uri: '/holidays', status: NOT_FOUND
    }
}
