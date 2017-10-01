package ni.edu.ucc.leon.coordination

import grails.validation.ValidationException
import ni.edu.ucc.leon.CoordinationService
import ni.edu.ucc.leon.CoordinationColor
import ni.edu.ucc.leon.ColorService
import ni.edu.ucc.leon.Coordination
import ni.edu.ucc.leon.Color

class CoordinationController {

    @Autowired CoordinationService coordinationService
    @Autowired ColorService colorService

    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def index() {
        respond coordinationService.list()
    }

    def create() {
        respond new Coordination(params), model: [colorList: colorService.findAll()]
    }

    def save() {
        try {
            List<Long> colors = params.list('colors').collect { color -> color.toLong() } as List
            Coordination coordination = coordinationService.save(params.name, params.acronym, params.extensionNumber, params.int('copyFee'), params.area, colors)

            flash.message = 'Coordinacion guardada'
            redirect coordination
        } catch(ValidationException e) {
            respond e.errors, view: 'create'
        }
    }

    def show(final Long id) {
        Coordination coordination = coordinationService.find(id)
        List<Color> colorList = CoordinationColor.findAllByCoordination(coordination)

        respond coordination, model: [colorList: colorList]
    }

    def edit(final Long id) {
        Coordination coordination = coordinationService.find(id)

        respond coordination, model: [
            colorList: colorService.findAll(),
            coordinationColorList: CoordinationColor.findAllByCoordination(coordination)
        ]
    }

    def update() {
        try {
            List<Long> colors = params.list('colors').collect { color -> color.toLong() } as List
            Coordination coordination = coordinationService.update(params.long('id'), params.name, params.acronym, params.extensionNumber, params.int('copyFee'), params.area, colors)

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

    def delete(final Long id) {
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
