package ni.edu.ucc.leon.guest

import grails.validation.ValidationException
import ni.edu.ucc.leon.GuestService
import ni.edu.ucc.leon.Guest

class GuestController {

    GuestService guestService

    def index() {
        respond guestService.findAll()
    }

    def show(final Long id) {
        respond id ? guestService.find(id) : null
    }

    def save(final String fullName) {
        try {
            Guest guest = guestService.save(fullName)

            flash.message = 'Invitado agregado'
            redirect guest
        } catch(ValidationException e) {
            respond e, model: [guestList: guestService.findAll()], view: 'index'
        }
    }

    def edit(final Long id) {
        respond id ? guestService.find(id) : null
    }

    def update(final Long id, final String fullName) {
        try {
            Guest guest = guestService.update(id, fullName)

            flash.message = 'Invitado actualizado'
            redirect guest
        } catch(ValidationException e) {
            respond e, model: [guestList: guestService.list()], view: 'edit'
        }
    }

    def delete(final Long id) {
        Guest guest = guestService.delete(id)

        if (!guest) response.sendError 404

        flash.message = 'Invitado eliminado'
        redirect uri: '/guests', method: 'GET'
    }
}
