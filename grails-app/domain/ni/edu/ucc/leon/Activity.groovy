package ni.edu.ucc.leon

class Activity {

    String name
    String state = 'created'
    Coordination organizedBy
    Employee authorizedBy
    Date authorizationDate
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false
        state inList: Helper.ACTIVITY_STATE_LIST, maxSize: 100
        authorizedBy nullable: true
        authorizationDate nullable: true
    }

    static belongsTo = [employee: Employee]

    static hasMany = [locations: Location, observations: Observation, employeeVouchers: EmployeeVoucher, guestVouchers: GuestVoucher]

    static mapping = {
        version false
        table 'activities'
        sort dateCreated: 'desc'
        state column: 'state', sqlType: 'varchar(100)', defaultValue: 'created'
        locations cascade: 'all-delete-orphan'
        observations cascade: 'all-delete-orphan'
    }
}
