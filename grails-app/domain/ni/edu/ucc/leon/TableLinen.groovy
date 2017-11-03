package ni.edu.ucc.leon

class TableLinen {

    Location location
    Color color

    static constraints = {
        location nullable: false
        color nullable: false
    }

    static mapping = {
        version false
        table 'tablecloths'
    }

    static TableLinen create(Color color, Location location) {
        new TableLinen(color: color, location: location).save(flush: true)
    }

    static List<Color> findAllByLocation(Location location) {
        TableLinen.where { location == location }.list().color
    }

    static Integer removeAll(Location location) {
        TableLinen.where { location == location }.deleteAll() as Integer
    }
}
