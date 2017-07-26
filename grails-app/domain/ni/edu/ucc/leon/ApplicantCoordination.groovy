package ni.edu.ucc.leon

class ApplicantCoordination {
    Coordination coordination

    static belongsTo = [ticket: Ticket]

    static mapping = {
        version false
    }
}
