databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1507236886414-1") {
        addColumn(tableName: "locations") {
            column(name: "projector_table", type: "bit") {
                constraints(nullable: "false")
            }
        }
    }
}
