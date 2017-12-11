package ni.edu.ucc.leon

class Guest {

    String fullName
    Date dateCreated
    Date lastUpdated

    static constraints = {
        fullName blank: false
    }

    static mapping = {
        table 'guests'
        version false
        sort 'fullName'
    }
}
