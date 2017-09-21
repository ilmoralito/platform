databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1506017902325-1") {
        createTable(tableName: "activities") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "activitiesPK")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "authorized_by_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(defaultValue: "created", name: "state", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "authorization_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "organized_by_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "employee_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "class", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "confirmation_date", type: "datetime")

            column(name: "confirmed_by_id", type: "BIGINT")

            column(name: "approval_date", type: "datetime")

            column(name: "approved_by_id", type: "BIGINT")
        }
    }

    changeSet(author: "sherlock (generated)", id: "1506017902325-2") {
        createTable(tableName: "observations") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "observationsPK")
            }

            column(name: "activity_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "state", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1506017902325-3") {
        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "activities", constraintName: "FKak1bh3me1lqlnovapvrtg8qi2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employees")
    }

    changeSet(author: "sherlock (generated)", id: "1506017902325-4") {
        addForeignKeyConstraint(baseColumnNames: "organized_by_id", baseTableName: "activities", constraintName: "FKdbm7vvbc467t9w6k5hnh4laql", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "coordinations")
    }

    changeSet(author: "sherlock (generated)", id: "1506017902325-5") {
        addForeignKeyConstraint(baseColumnNames: "activity_id", baseTableName: "observations", constraintName: "FKh5hf8q4fpu8mvpb692ccvojy0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "activities")
    }

    changeSet(author: "sherlock (generated)", id: "1506017902325-6") {
        addForeignKeyConstraint(baseColumnNames: "authorized_by_id", baseTableName: "activities", constraintName: "FKkh1017wmbqh6kc8mlgumoeydd", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employees")
    }

    changeSet(author: "sherlock (generated)", id: "1506017902325-7") {
        addForeignKeyConstraint(baseColumnNames: "employee_id", baseTableName: "activities", constraintName: "FKolqfxude3ki5reapiyat5fc98", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employees")
    }

    changeSet(author: "sherlock (generated)", id: "1506017902325-8") {
        addForeignKeyConstraint(baseColumnNames: "confirmed_by_id", baseTableName: "activities", constraintName: "FKqu44judy4rg556fhv7fynmy7x", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employees")
    }

    changeSet(author: "sherlock (generated)", id: "1506017902325-9") {
        addForeignKeyConstraint(baseColumnNames: "approved_by_id", baseTableName: "activities", constraintName: "FKrg7imyg4aaytbasabrvw0y9d1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employees")
    }

    changeSet(author: "sherlock (generated)", id: "1506017902325-10") {
        dropDefaultValue(columnDataType: "boolean", columnName: "air_conditioned", tableName: "classrooms")
    }

    changeSet(author: "sherlock (generated)", id: "1506017902325-11") {
        dropDefaultValue(columnDataType: "boolean", columnName: "ethernet", tableName: "datashows")
    }

    changeSet(author: "sherlock (generated)", id: "1506017902325-12") {
        dropDefaultValue(columnDataType: "boolean", columnName: "hdmi", tableName: "datashows")
    }

    changeSet(author: "sherlock (generated)", id: "1506017902325-13") {
        dropDefaultValue(columnDataType: "boolean", columnName: "scheduled", tableName: "tickets")
    }

    changeSet(author: "sherlock (generated)", id: "1506017902325-14") {
        dropDefaultValue(columnDataType: "boolean", columnName: "wifi", tableName: "classrooms")
    }

    changeSet(author: "sherlock (generated)", id: "1506017902325-15") {
        dropDefaultValue(columnDataType: "boolean", columnName: "wifi", tableName: "datashows")
    }
}
