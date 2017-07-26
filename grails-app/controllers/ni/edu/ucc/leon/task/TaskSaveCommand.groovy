package ni.edu.ucc.leon.task

import grails.compiler.GrailsCompileStatic
import grails.validation.Validateable

@GrailsCompileStatic
class TaskSaveCommand implements Validateable {
    Long ticketId
    String description

    static constraints = {
        description blank: false
    }
}
