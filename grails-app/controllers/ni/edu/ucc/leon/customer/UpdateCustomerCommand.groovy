package ni.edu.ucc.leon.customer

class UpdateCustomerCommand extends SaveCustomerCommand implements grails.validation.Validateable {

    Long id

    static constraints = {
        id blank: false, nullable: false
    }
}