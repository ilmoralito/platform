package ni.edu.ucc.leon.location

import grails.databinding.BindingFormat
import ni.edu.ucc.leon.Refreshment

class SaveLocationCommand implements grails.validation.Validateable {

    Long employeeId
    Long activityId
    Long place

    @BindingFormat('yyyy-MM-dd')
    Date startDateAndTime
    List<String> interval
    Integer participants
    String typeOfAssembly
    Boolean datashow
    Boolean podium
    Boolean displayTable
    Boolean flags
    Boolean water
    Boolean coffee
    Boolean nationalAnthem
    Boolean universityAnthem
    Boolean triumphalAnthem
    Boolean sound
    Boolean projectorTable
    Integer waterBottles
    List<Long> colors
    String observation
    // Refreshment refreshment

    static constraints = {
        interval nullable: false, minSize: 2
        colors nullable: false, minSize: 1
        observation nullable: true
        // refreshment nullable: true, validator: { refreshment, object, error ->
        //     if (!refreshment.food && !refreshment.quantity) {
        //         return
        //     }

        //     if (!refreshment.validate(['food', 'drink', 'quantity'])) {
        //         refreshment.errors.allErrors.each { e ->
        //             error.rejectValue "refreshment.${e.arguments[0]}", "${e.objectName}.${e.arguments[0]}.${e.code}", e.arguments, "${e.objectName}.${e.arguments[0]}.${e.code}"
        //         }
        //     }
        // }
    }

    def beforeValidate() {
        waterBottles = waterBottles ?: 0
    }
}
