package ni.edu.ucc.leon

class Holiday {

    Date date
    String name
    String wiki
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false
        wiki nullable: true, url: true, maxSize: 255
    }

    static mapping = {
        table 'holidays'
        version false
    }
}
