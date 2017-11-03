package ni.edu.ucc.leon

import groovy.transform.AutoClone

@AutoClone
class Location {

    Classroom place
    Date startDateAndTime
    Date endDateAndTime
    Integer participants
    String typeOfAssembly
    Boolean datashow = false
    Boolean podium = false
    Boolean displayTable = false
    Boolean flags = false
    Boolean water = false
    Boolean coffee = false
    Boolean nationalAnthem = false
    Boolean universityAnthem = false
    Boolean triumphalAnthem = false
    Boolean sound = false
    Boolean projectorTable = false
    Integer waterBottles
    String observation
    Refreshment refreshment

    Date dateCreated
    Date lastUpdated

    static belongsTo = [activity: Activity]

    static constraints = {
        startDateAndTime validator: ConstraintsUtils.validateStartDateAndTime()
        endDateAndTime validator: ConstraintsUtils.validateEndDateAndTime()
        participants min: 1
        typeOfAssembly inList: ['Auditorio con mesas', 'Auditorio sin mesas', 'En forma de U', 'Libre'], maxSize: 255
        refreshment nullable: true
        waterBottles nullable: true, min: 0
        observation nullable: true
    }

    static mapping = {
        version false
        table 'locations'
        datashow defaultValue: false
        podium defaultValue: false
        displayTable defaultValue: false
        flags defaultValue: false
        water defaultValue: false
        coffee defaultValue: false
        nationalAnthem defaultValue: false
        universityAnthem defaultValue: false
        triumphalAnthem defaultValue: false
        sound defaultValue: false
        projectorTable defaultValue: false
        waterBottles defaultValue: 0
        observation type: 'text'
    }
}
