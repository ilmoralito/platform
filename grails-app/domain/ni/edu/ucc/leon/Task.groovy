package ni.edu.ucc.leon

class Task {
    User createdBy
    String description
    String state = 'default'

    Date dateCreated
    Date lastUpdated

    static constraints = {
        description blank: false
        state blank: false, inList: ['default', 'info', 'warning']
    }

    static belongsTo = [ticket: Ticket]

    static mapping = {
        table 'tasks'
        version false
        sort dateCreated: 'desc'
        description type: 'text'
        state defaultValue: "'default'"
    }
}
