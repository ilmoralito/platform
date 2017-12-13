databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1513007469413-1") {
        createTable(tableName: "coordinations_guests") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "coordinations_guestsPK")
            }

            column(name: "guest_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "coordination_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1513007469413-2") {
        addForeignKeyConstraint(baseColumnNames: "coordination_id", baseTableName: "coordinations_guests", constraintName: "FK7a8hfu7j5hd6vatsddign20f1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "coordinations")
    }

    changeSet(author: "sherlock (generated)", id: "1513007469413-3") {
        addForeignKeyConstraint(baseColumnNames: "guest_id", baseTableName: "coordinations_guests", constraintName: "FKmo47stw41v427cq3vaxathoye", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "guests")
    }
}
