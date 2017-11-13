package ni.edu.ucc.leon

class EmployeeCoordination {
    Employee employee
    Coordination coordination
    String position
    String jobTitle

    static constraints = {
        position inList: ['manager', 'assistant'], maxSize: 100
        jobTitle blank: false
    }

    public static final EmployeeCoordination create(Employee employee, Coordination coordination, String position, String jobTitle) {
        new EmployeeCoordination(
            employee: employee,
            coordination: coordination,
            position: position,
            jobTitle: jobTitle
        ).save(flush: true)
    }

    public static final List<EmployeeCoordination> listByEmployee(Employee employee) {
        EmployeeCoordination.where { employee == employee }.list()
    }

    public static final Boolean exists(Employee employee, Coordination coordination) {
        EmployeeCoordination.where { employee == employee && coordination == coordination }.count()
    }

    public static final List<Coordination> listEmployeeCoordinations(final Employee employee) {
        EmployeeCoordination.where { employee == employee }.list().coordination
    }

    static mapping = {
        table 'employee_coordinations'
        version false
    }
}
