package ni.edu.ucc.leon

class Representative {

    String fullName
    String email
    String telephoneNumber
    String identificationCard
    String academicTitle

    static constraints = {
        fullName blank: false
        email blank: false, unique: true, email: true
        telephoneNumber blank: false, unique: true, maxSize: 8, minSize: 8
        identificationCard blank: false, unique: true
        academicTitle inList: Helper.TITLES
    }

    static belongsTo = [customer: Customer]

    static mapping = {
        version false
        customer unique: true
        table 'representatives'
        telephoneNumber sqlType: 'varchar(8)'
        academicTitle sqlType: 'varchar(100)'
    }

    String getNameAndTitle() {
        "$academicTitle $fullName"
    }

    static transients = ['nameAndTitle']
}
