package ni.edu.ucc.leon

import groovy.transform.ToString

@ToString
class Voucher {

    Date date
    CoffeeShop coffeeShop
    Integer price
    Coordination coordination
    Boolean breakfast = false
    Boolean lunch = false
    Boolean dinner = false
    Boolean others = false

    Date dateCreated
    Date lastUpdated

    static constraints = {
        date blank: false
        price min: 1
    }

    static mapping = {
        table 'vouchers'
        version false
        sort 'date'
        breakfast column: 'breakfast', sqlType: 'BOOL', defaultValue: false
        lunch column: 'lunch', sqlType: 'BOOL', defaultValue: false
        dinner column: 'dinner', sqlType: 'BOOL', defaultValue: false
        others column: 'others', sqlType: 'BOOL', defaultValue: false
    }
}
