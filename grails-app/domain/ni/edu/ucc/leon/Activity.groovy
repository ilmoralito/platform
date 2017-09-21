package ni.edu.ucc.leon

class Activity {

    String name
    String state = 'created'
    Employee createdBy
    Coordination organizedBy
    Employee authorizedBy
    Date authorizationDate
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false
        state inList: ['created', 'confirmed', 'approved', 'authorized', 'canceled'], maxSize: 100
    }

    static belongsTo = [employee: Employee]

    static hasMany = [observations: Observation]

    static mapping = {
        version false
        table 'activities'
        state defaultValue: "'created'"
    }
}
