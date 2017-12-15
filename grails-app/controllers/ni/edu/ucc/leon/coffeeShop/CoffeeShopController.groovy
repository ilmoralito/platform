package ni.edu.ucc.leon.coffeeshop

import grails.validation.ValidationException
import ni.edu.ucc.leon.CoffeeShopService
import ni.edu.ucc.leon.CoffeeShop

class CoffeeShopController {

    CoffeeShopService coffeeShopService

    def index() {
        respond coffeeShopService.findAll()
    }

    def save(SaveCoffeeShopCommand command) {
        if (command.hasErrors()) {
            respond ([errors: command.errors], model: [coffeeShopList: coffeeShopService.findAll()], view: 'index')

            return
        }

        try {
            CoffeeShop coffeeShop = coffeeShopService.save(command.name, command.breakfast, command.lunch, command.dinner, command.others)

            flash.message = 'Cafeteria creada'
            redirect coffeeShop
        } catch(ValidationException e) {
            respond ([errors: e.errors], view: 'index')
        }
    }

    def show(final Long id) {
        respond id ? coffeeShopService.find(id) : null
    }

    def edit(final Long id) {
        respond id ? coffeeShopService.find(id) : null
    }

    def update(UpdateCoffeeShopCommand command) {
        if (command.hasErrors()) {
            respond ([errors: command.errors], model: [coffeeShop: coffeeShopService.find(command.id)], view: 'edit')

            return
        }

        try {
            CoffeeShop coffeeShop = coffeeShopService.update(
                command.id, command.name, command.breakfast, command.lunch, command.dinner, command.others
            )

            flash.message = 'Cafetin actualizado'
            redirect coffeeShop
        } catch(ValidationException e) {
            respond ([errors: e.errors], model: [coffeeShop: e], view: 'edit')
        }
    }

    def delete(final Long id) {
        CoffeeShop coffeeShop = coffeeShopService.delete(id)

        if (!coffeeShop) response.sendError 404

        flash.message = 'Cafetin eliminado'
        redirect uri: '/coffeeShops', method: 'GET'
    }
}
