package ni.edu.ucc.leon.voucher

import grails.databinding.BindingFormat

import ni.edu.ucc.leon.Coordination
import ni.edu.ucc.leon.CoffeeShop
import ni.edu.ucc.leon.Employee

class SaveFixedVoucher implements grails.validation.Validateable {

    @BindingFormat('yyyy-MM-dd')
    Date date
    CoffeeShop coffeeShop
    Employee employee
    Coordination coordination
    Integer price
    Boolean breakfast
    Boolean lunch
    Boolean dinner
    Boolean others

    static constraints = {
        date validator: { date -> date >= new Date().clearTime() }
        price min: 1
        breakfast nullable: true
        lunch nullable: true
        dinner nullable: true
        others nullable: true, validator: { others, obj -> obj.breakfast || obj.lunch || obj.dinner || others }
    }
}
