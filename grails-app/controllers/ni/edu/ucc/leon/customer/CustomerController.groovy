package ni.edu.ucc.leon.customer

import grails.validation.ValidationException
import ni.edu.ucc.leon.CustomerService
import ni.edu.ucc.leon.Customer
import ni.edu.ucc.leon.Helper

class CustomerController {

    CustomerService customerService

    def index() {
        respond customerService.findAll()
    }

    def create() {
        respond ([customer: new Customer(params)], model: [academicTitleList: Helper.TITLES])
    }

    def save(SaveCustomerCommand command) {
        if (command.hasErrors()) {
            respond ([errors: command.errors], view: 'create')

            return
        }

        try {
            Customer customer = customerService.save(command)

            flash.message = 'Cliente creado'
            redirect customer
        } catch(ValidationException e) {
            respond ([errors: e.errors], view: 'create')
        }
    }

    def show(final Long id) {
        respond id ? customerService.find(id) : null
    }

    def edit(final Long id) {
        respond ([customer: id ? customerService.find(id) : null], model: [academicTitleList: Helper.TITLES])
    }

    def update(UpdateCustomerCommand command) {
        if (command.hasErrors()) {
            respond ([errors: command.errors], model: [customer: customerService.find(command.id)], view: 'edit')

            return
        }

        try {
            Customer customer = customerService.update(command)

            flash.message = 'Cliente actualizado'
            redirect customer
        } catch(ValidationException e) {
            respond ([errors: e.errors], model: [customer: customerService.find(command.id)], view: 'edit')
        }
    }

    def delete(final Long id) {
        Customer customer = customerService.delete(id)

        if (!customer) {
            notFound()

            return
        }

        flash.message = 'Cliente eliminado'
        redirect uri: '/customers', method: 'GET'
    }

    private void notFound() {
        flash.message = 'Cliente no encontrado'

        redirect uri: '/customers', method: 'GET'
    }
}
