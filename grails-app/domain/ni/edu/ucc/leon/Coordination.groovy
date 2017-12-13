package ni.edu.ucc.leon

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class Coordination {

    String name
    String acronym
    String extensionNumber
    Integer copyFee
    String area

    static constraints = {
        name blank: false, unique: true
        acronym nullable: true
        extensionNumber blank: false
        copyFee nullable: true, min: 1
        area inList: ['academic', 'administrative', 'school'], maxSize: 100
    }

    static mapping = {
        table 'coordinations'
        version false
        sort 'name'
    }
}
