package ni.edu.ucc.leon.user

import grails.compiler.GrailsCompileStatic
import grails.validation.Validateable

@GrailsCompileStatic
class UserSaveCommand implements Validateable {

    Long employeeId
    String email
    List<String> roles

    static constraints = {
        email blank: false, unique: true, email: true
        roles nullable: false, minSize: 1
    }
}
