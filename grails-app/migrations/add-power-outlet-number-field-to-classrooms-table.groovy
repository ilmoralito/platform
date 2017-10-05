databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1507212818462-1") {
        addColumn(tableName: "classrooms") {
            column(defaultValueNumeric: "1", name: "power_outlet_number", type: "integer") {
                constraints(nullable: "false")
            }
        }
    }
}
