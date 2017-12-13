package ni.edu.ucc.leon.employee

class SaveGuestCommand implements grails.validation.Validateable {

    Long employeeId
    String coordination
    List<Long> guestList

    static constraints = {
        employeeId nullable: false
        coordination blank: false
        guestList nullable: false, minSize: 1
    }
}