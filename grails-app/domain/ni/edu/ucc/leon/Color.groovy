package ni.edu.ucc.leon

class Color {

    String name

    static constraints = {
        name blank: false, unique: true
    }

    static mapping = {
        version false
        table 'colors'
        sort 'name'
    }
}
