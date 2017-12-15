package ni.edu.ucc.leon.coffeeshop

class SaveCoffeeShopCommand implements grails.validation.Validateable {

    String name
    Integer breakfast
    Integer lunch
    Integer dinner
    Integer others

    static constraints = {
        importFrom ni.edu.ucc.leon.CoffeeShop
    }
}