package ni.edu.ucc.leon

class Datashow {
    String trademark
    String model
    String serialNumber
    Integer code
    Boolean hdmi = false
    Boolean wifi = false
    Boolean ethernet = false
    Boolean enabled = true

    Date dateCreated
    Date lastUpdated

    static constraints = {
        trademark blank: false
        model blank: false
        serialNumber blank: false, unique: true
        code blank: false, unique: true
    }

    static mapping = {
        version false
        table 'datashows'
        sort code: 'asc'
        hdmi defaultValue: "0"
        wifi defaultValue: "0"
        ethernet defaultValue: "0"
        enabled defaultValue: "1"
    }
}
