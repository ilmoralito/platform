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
        capacity min: 1
        powerOutletNumber min: 1
    }

    static mapping = {
        table 'classrooms'
        version false
        sort 'code'
        wifi column: 'wifi', defaultValue: false
        airConditioned column: 'air_conditioned', defaultValue: false
        powerOutletNumber column: 'power_outlet_number', defaultValue: 1
    }
}
