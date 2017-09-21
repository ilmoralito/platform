package ni.edu.ucc.leon

class Observation {

    String description
    String state

    static constraints = {
        description blank: false
        state inList: ['success', 'info', 'warning', 'danger'], maxSize: 100
    }

    static belongsTo = [activity: Activity]

    static mapping = {
        version false
        table 'observations'
    }
}
