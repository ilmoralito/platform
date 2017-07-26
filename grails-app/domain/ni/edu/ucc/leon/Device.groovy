package ni.edu.ucc.leon

class Device {
    String name

    static constraints = {
        name blank: false, unique: true
    }

    static mapping = {
        version false
        table 'devices'
        sort name: 'asc'
    }
}
