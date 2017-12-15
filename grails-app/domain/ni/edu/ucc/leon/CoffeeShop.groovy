package ni.edu.ucc.leon

import groovy.transform.ToString

@ToString
class CoffeeShop {

    String name
    Integer breakfast
    Integer lunch
    Integer dinner
    Integer others

    static constraints = {
        name blank: false, unique: true
        breakfast min: 1
        lunch min: 1
        dinner min: 1
        others min: 1
    }

    static mapping = {
        table 'coffeeshops'
        version false
        name sqlType: 'varchar(100)'
    }
}
