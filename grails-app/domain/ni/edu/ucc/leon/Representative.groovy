package ni.edu.ucc.leon

class Representative {

    String fullName
    String email
    String telephoneNumber
    String identificationCard

    static constraints = {
        fullName blank: false
        email blank: false, unique: true, email: true
        telephoneNumber blank: false, unique: true, maxSize: 8, minSize: 8
        identificationCard blank: false, unique: true
    }

    static belongsTo = [customer: Customer]

    static mapping = {
        version false
        customer unique: true
        table 'representatives'
        telephoneNumber column: 'telephone_number', length: 8
    }
}
