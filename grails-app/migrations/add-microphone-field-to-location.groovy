databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1510869034244-1") {
        addColumn(tableName: "locations") {
            column(name: "microphone", type: "bit") {
                constraints(nullable: "false")
            }
        }
    }
}
