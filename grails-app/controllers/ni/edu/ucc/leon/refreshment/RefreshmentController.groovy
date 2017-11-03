package ni.edu.ucc.leon.refreshment

import grails.validation.ValidationException
import ni.edu.ucc.leon.RefreshmentService
import ni.edu.ucc.leon.Refreshment

class RefreshmentController {

    @Autowired
    RefreshmentService refreshmentService

    def create() {
        respond new Refreshment(params)
    }

    def save(final String food, final String drink, final Integer quantity, final Long locationId) {
        try {
            refreshmentService.save(food, drink, quantity, locationId)

            flash.message = 'Refrigerio agregado'
            redirect uri: "/employees/${params.employeeId}/activities/${params.activityId}/locations/$locationId", method: 'GET'
        } catch(ValidationException e) {
            respond e.errors, view: 'create'
        }
    }

    def edit(final Long id) {
        respond id ? refreshmentService.find(id) : null
    }

    def update(final Long id, final String food, final String drink, final Integer quantity) {
        try {
            refreshmentService.update(id, food, drink, quantity)

            flash.message = 'Refrigerios actualizado'
            redirect uri: "/employees/${params.employeeId}/activities/${params.activityId}/locations/${params.locationId}", method: 'GET'
        } catch(ValidationException e) {
            respond e.errors, view: 'edit'
        }
    }

    def delete(final Long id, final Long locationId) {
        refreshmentService.delete(id, locationId)

        flash.message = 'Refrigerio eliminado'
        redirect uri: "/employees/${params.employeeId}/activities/${params.activityId}/locations/${params.locationId}", method: 'GET'
    }
}
