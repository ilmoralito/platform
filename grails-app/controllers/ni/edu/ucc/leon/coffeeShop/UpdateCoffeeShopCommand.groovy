package ni.edu.ucc.leon.coffeeshop

class UpdateCoffeeShopCommand extends SaveCoffeeShopCommand implements grails.validation.Validateable {

    Long id
    String name
    Integer breakfast
    Integer lunch
    Integer dinner
    Integer others

    static constraints = {
        importFrom ni.edu.ucc.leon.CoffeeShop
    }
}