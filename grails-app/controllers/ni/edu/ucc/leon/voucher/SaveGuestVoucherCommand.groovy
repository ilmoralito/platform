package ni.edu.ucc.leon.voucher

import grails.databinding.BindingFormat
import ni.edu.ucc.leon.CoffeeShop
import ni.edu.ucc.leon.Guest

class SaveGuestVoucherCommand implements grails.validation.Validateable {

    Long employeeId
    Long activityId
    String type

    @BindingFormat('yyyy-MM-dd')
    Date date
    CoffeeShop coffeeShop
    List<Guest> guests
    Integer price
    Boolean breakfast
    Boolean lunch
    Boolean dinner
    Boolean others

    static constraints = {
        guests min: 1, nullable: false
        price min: 1
        breakfast nullable: true
        lunch nullable: true
        dinner nullable: true
        others nullable: true, validator: { others, obj ->
            obj.breakfast || obj.lunch || obj.dinner || others
        }
    }
}