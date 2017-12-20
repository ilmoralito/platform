package ni.edu.ucc.leon

class Employee {

    String fullName
    String identityCard
    String contract = 'permanent'
    Boolean enabled = true
    User user

    Date dateCreated
    Date lastUpdated

    static constraints = {
        user nullable: true
        fullName blank: false
        identityCard blank: false, unique: true
        contract inList: ['permanent', 'schedule'], maxSize: 100
        user nullable: true
    }

    Set<Coordination> getCoordinations() {
        (EmployeeCoordination.findAllByEmployee(this) as List<EmployeeCoordination>)*.coordination as Set<Coordination>
    }

    static hasMany = [tickets: Ticket]

    static mapping = {
        table 'employees'
        version false
        sort 'fullName'
        enabled defaultValue: true
        contract defaultValue: "'permanent'"
    }
}
