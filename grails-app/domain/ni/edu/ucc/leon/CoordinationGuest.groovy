package ni.edu.ucc.leon

class CoordinationGuest {

    Coordination coordination
    Guest guest

    static constraints = {
        coordination nullable: false
        guest nullable: false
    }

    static mapping = {
        table 'coordinations_guests'
        version false
    }

    public static final CoordinationGuest create(Coordination coordination, Guest guest) {
        new CoordinationGuest(coordination: coordination, guest: guest).save(flush: true)
    }

    public static final int removeAll(Coordination coordination) {
        !coordination ? 0 : CoordinationGuest.where { coordination == coordination }.deleteAll() as int
    }

    public static final List<Guest> guestList(Coordination coordination) {
        CoordinationGuest.where { coordination == coordination }.list().guest
    }
}
