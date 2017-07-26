package ni.edu.ucc.leon

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String email
    String password = 'temporal'
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        email nullable: false, blank: false, unique: true, email: true
        password nullable: false, blank: false, password: true
        employee nullable: true
    }

    static belongsTo = [employee: Employee]

    static mapping = {
        table 'users'
        password column: '`password`'
    }
}
