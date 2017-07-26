package ni.edu.ucc.leon

class Bookmark {
    Employee employee
    Date dateCreated

    static constraints = {
        employee nullable: false
    }

    static mapping = {
        version false
        table 'bookmarks'
        sort dateCreated: 'desc'
    }
}
