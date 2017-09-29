package ni.edu.ucc.leon

class CoordinationColor {

    Color color
    Coordination coordination

    static constraints = {
        color nullable: false
        coordination nullable: false
    }

    static mapping = {
        version false
        table 'coordination_colors'
    }
}
