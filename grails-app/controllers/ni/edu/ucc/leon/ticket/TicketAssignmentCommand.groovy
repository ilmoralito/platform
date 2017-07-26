package ni.edu.ucc.leon.ticket

import grails.compiler.GrailsCompileStatic
import grails.validation.Validateable

@GrailsCompileStatic
class TicketAssignmentCommand implements Validateable {
    Long id
    String device

    static constraints = {
        id nullable: false
        device blank: false
    }
}
