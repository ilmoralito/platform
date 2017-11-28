databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1511888880994-1") {
        addColumn(tableName: "representatives") {
            column(name: "academic_title", type: "varchar(100)") {
                constraints(nullable: "false")
            }
        }
    }
}
