databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1507129443561-1") {
        createTable(tableName: "locations") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "locationsPK")
            }

            column(name: "podium", type: "BIT") {
                constraints(nullable: "false")
            }

            column(name: "end_date_and_time", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "refreshment_id", type: "BIGINT")

            column(name: "activity_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "sound", type: "BIT") {
                constraints(nullable: "false")
            }

            column(name: "coffee", type: "BIT") {
                constraints(nullable: "false")
            }

            column(name: "water_bottles", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "anthem", type: "BIT") {
                constraints(nullable: "false")
            }

            column(name: "observation", type: "LONGTEXT")

            column(name: "type_of_assembly", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "flags", type: "BIT") {
                constraints(nullable: "false")
            }

            column(name: "place_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "water", type: "BIT") {
                constraints(nullable: "false")
            }

            column(name: "datashow", type: "BIT") {
                constraints(nullable: "false")
            }

            column(name: "participants", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "start_date_and_time", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "display_table", type: "BIT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1507129443561-2") {
        createTable(tableName: "refreshments") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "refreshmentsPK")
            }

            column(name: "food", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "quantity", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "drink", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "sherlock (generated)", id: "1507129443561-3") {
        createTable(tableName: "tablecloths") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "tableclothsPK")
            }

            column(name: "location_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "color_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1507129443561-4") {
        addForeignKeyConstraint(baseColumnNames: "place_id", baseTableName: "locations", constraintName: "FK4aeto40fas49pjmdo7eynwblp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "classrooms")
    }

    changeSet(author: "sherlock (generated)", id: "1507129443561-5") {
        addForeignKeyConstraint(baseColumnNames: "color_id", baseTableName: "tablecloths", constraintName: "FKhuy8r4vqeytw5j1hn6yw7v17u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "colors")
    }

    changeSet(author: "sherlock (generated)", id: "1507129443561-6") {
        addForeignKeyConstraint(baseColumnNames: "activity_id", baseTableName: "locations", constraintName: "FKih0l99m6fjlhaew64iqlv55xw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "activities")
    }

    changeSet(author: "sherlock (generated)", id: "1507129443561-7") {
        addForeignKeyConstraint(baseColumnNames: "location_id", baseTableName: "tablecloths", constraintName: "FKmhm8lyuwh5b0nv5enrv4tdhpe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "locations")
    }

    changeSet(author: "sherlock (generated)", id: "1507129443561-8") {
        addForeignKeyConstraint(baseColumnNames: "refreshment_id", baseTableName: "locations", constraintName: "FKmm3wvkdve2dtmqhy5wxggy9bw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "refreshments")
    }
}
