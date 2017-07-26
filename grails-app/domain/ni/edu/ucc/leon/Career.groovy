package ni.edu.ucc.leon

class Career {
    String name

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, unique: true
    }

    static mapping = {
        table 'careers'
        verison false
        sort 'name'
    }
}
