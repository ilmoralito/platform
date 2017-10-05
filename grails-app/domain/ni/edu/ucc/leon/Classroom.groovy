package ni.edu.ucc.leon

class Classroom {
    String code
    String name
    Integer capacity
    Boolean wifi = false
    Boolean airConditioned = false
    Integer powerOutletNumber

    Date dateCreated
    Date lastUpdated

    static constraints = {
        code blank: false, unique: true
        name nullable: true
        capacity nullable: true, validator: { capacity ->
            if (capacity) {
                capacity > 0
            }
        }
        powerOutletNumber min: 1
    }

    static mapping = {
        table 'classrooms'
        version false
        sort 'code'
        wifi defaultValue: false
        airConditioned defaultValue: false
        powerOutletNumber defaultValue: 1
    }
}
