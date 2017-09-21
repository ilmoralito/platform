package ni.edu.ucc.leon

class Ticket {
    Long id
    String status = 'open'
    Device device
    String subject
    Boolean scheduled = false

    Date dateCreated
    Date lastUpdated

    static constraints = {
        subject blank: false
        device nullable: true
        applicantCoordinations nullable: false, min: 1
        status inList: ['open', 'pending', 'closed'], maxSize: 100
    }

    static belongsTo = [employee: Employee]

    static hasMany = [
        tasks: Task,
        applicantCoordinations: ApplicantCoordination
    ]

    static mapping = {
        version false
        table 'tickets'
        sort dateCreated: 'desc'
        observation type: 'text'
        status defaultValue: "'open'"
        scheduled defaultValue: false
        tasks sort: 'dateCreated', order: 'desc'
        ticketAttendedBy cascade: 'all-delete-orphan'
        applicantCoordinations cascade: 'all-delete-orphan'
    }
}
