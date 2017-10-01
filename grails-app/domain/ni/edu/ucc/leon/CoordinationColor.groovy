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

    static CoordinationColor create(Color color, Coordination coordination) {
        new CoordinationColor(color: color, coordination: coordination).save(flush: true)
    }

    static List<Color> findAllByCoordination(Coordination coordination) {
        CoordinationColor.where { coordination == coordination }.list().color
    }

    static Integer removeAll(Coordination coordination) {
        CoordinationColor.where { coordination == coordination }.deleteAll() as Integer
    }
}
