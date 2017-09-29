package ni.edu.ucc.leon.color

import grails.validation.ValidationException
import ni.edu.ucc.leon.ColorService
import ni.edu.ucc.leon.Color

class ColorController {

    @Autowired ColorService colorService

    static allowedMethods = [ save: 'POST', update: 'PUT', delete: 'DELETE' ]

    def index() {
        respond colorService.findAll()
    }

    def save(final String name) {
        try {
            Color color = colorService.save(name)

            flash.message = 'Nuevo color agregado'
            redirect uri: '/colors', method: 'GET'
        } catch(ValidationException e) {
            respond e.errors, view: 'index', model: [colorList: colorService.findAll()]
        }
    }

    def show(final Long id) {
        respond id ? colorService.find(id) : null
    }

    def edit(final Long id) {
        respond id ? colorService.find(id) : null
    }

    def update(final Long id, final String name) {
        try {
            Color color = colorService.update(id, name)

            flash.message = 'Color actualizado'
            redirect color
        } catch(ValidationException e) {
            respond e.errors, view: 'edit'
        }
    }

    def delete(final Long id) {
        Color color = colorService.delete(id)

        if (!color) {
            notFound()
            return
        }

        flash.message = 'Color eliminado'
        redirect uri: '/colors', method: 'GET'
    }

    private void notFound() {
        flash.message = 'Color no encontrado'

        redirect uri: '/colors', method: 'GET'
    }
}
