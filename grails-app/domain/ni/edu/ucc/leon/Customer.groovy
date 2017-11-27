package ni.edu.ucc.leon

class Customer {

    String name
    Representative representative

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, unique: true
    }

    static mapping = {
        version false
        table 'customers'
    }
}
