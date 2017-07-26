package ni.edu.ucc.leon.employeeCoordination

import ni.edu.ucc.leon.EmployeeCoordinationService
import grails.validation.Validateable

class EmployeeCoordinationSaveCommand implements Validateable {

    EmployeeCoordinationService employeeCoordinationService

    Long coordinationId
    String position
    String jobTitle

    static constraints = {
        position inList: ['manager', 'assistant'], maxSize: 100, validator: { position, obj ->
            if (obj.position == 'manager') {
                if (obj.employeeCoordinationService.countManagersInCoordination(obj.coordinationId)) {
                    return 'not.allowed'
                }
            }
        }
        jobTitle blank: false
    }
}