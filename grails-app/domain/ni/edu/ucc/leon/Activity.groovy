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
        state inList: ['created', 'notified', 'confirmed', 'approved', 'authorized', 'canceled'], maxSize: 100
        authorizedBy nullable: true
        authorizationDate nullable: true
    }

    static belongsTo = [employee: Employee]

    static hasMany = [observations: Observation]

    static mapping = {
        version false
        table 'activities'
        sort dateCreated: 'desc'
        state defaultValue: "'created'"
        observations cascade: 'all-delete-orphan'
    }
}
