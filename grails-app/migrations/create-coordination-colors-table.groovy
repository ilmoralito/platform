databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1506629913804-1") {
        createTable(tableName: "coordination_colors") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "coordination_colorsPK")
            }

            column(name: "color_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "coordination_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1506629913804-4") {
        addForeignKeyConstraint(baseColumnNames: "coordination_id", baseTableName: "coordination_colors", constraintName: "FKc9dptlcmp3l4qd4wnt752d8ba", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "coordinations")
    }

    changeSet(author: "sherlock (generated)", id: "1506629913804-6") {
        addForeignKeyConstraint(baseColumnNames: "color_id", baseTableName: "coordination_colors", constraintName: "FKjr20m4ekdvj1fsmy10civqkk0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "colors")
    }
}
