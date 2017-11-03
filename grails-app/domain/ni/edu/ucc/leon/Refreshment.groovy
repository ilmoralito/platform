package ni.edu.ucc.leon

class Refreshment {

    String food
    String drink
    Integer quantity

    static constraints = {
        food inList: ['Galleta', 'Sandwich', 'Reposteria'], maxSize: 255
        drink nullable: true, inList: ['Gaseosa', 'Jugo', 'Te'], maxSize: 255
        quantity min: 0
    }

    static belongsTo = [location: Location]

    static mapping = {
        version false
        table 'refreshments'
        quantity defaultValue: 0
    }
}
