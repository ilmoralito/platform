package ni.edu.ucc.leon.employeeCoordination

class EmployeeCoordinationUpdateCommand extends EmployeeCoordinationSaveCommand {

    Long id

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