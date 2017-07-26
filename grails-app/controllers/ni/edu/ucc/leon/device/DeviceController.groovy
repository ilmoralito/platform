package ni.edu.ucc.leon.device

import static org.springframework.http.HttpStatus.NOT_FOUND
import grails.validation.ValidationException
import ni.edu.ucc.leon.DeviceService
import ni.edu.ucc.leon.Device

class DeviceController {
    DeviceService deviceService

    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

    def index() {
        respond deviceService.list()
    }

    def create() {
        respond new Device(params)
    }

    def save(String name) {
        try {
            Device device = deviceService.save(name)
            
            flash.message = 'Recurso agregado'
            redirect device
        }
        catch(ValidationException e) {
            flash.errors = e.errors
            redirect uri: '/devices'
        }
    }

    def show(Device device) {
        respond device
    }

    def edit(Device device) {
        respond device
    }

    def update(Long id, String name) {
        try {
            Device device = deviceService.update(id, name)

            if (!device) {
                notFound()
            } else {
                flash.message = 'Recurso actualizado'
                redirect device
            }
        } catch(ValidationException e) {
            flash.errors = e.errors
            redirect action: 'index', method: 'GET'
        }
        
    }

    def delete(Long id) {
        Device device = deviceService.delete(id)

        if (!device) {
            notFound()
        } else {
            flash.message = 'Recurso eliminado'

            redirect action: 'index', method: 'GET'
        }
    }

    protected void notFound() {
        flash.message = 'Recurso no encontrado'

        redirect action: 'index', status: NOT_FOUND
    }
}
