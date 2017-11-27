package ni.edu.ucc.leon.customer

class SaveCustomerCommand implements grails.validation.Validateable {

    String name
    String fullName
    String identificationCard
    String email
    String telephoneNumber

    static constraints = {
        name blank: false, unique: true
        fullName blank: false
        identificationCard blank: false, unique: true
        email blank: false, unique: true, email: true
        telephoneNumber blank: false, unique: true
    }
}