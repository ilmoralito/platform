databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1511711648329-1") {
        createTable(tableName: "customers") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "customersPK")
            }

            column(name: "representative_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1511711648329-2") {
        createTable(tableName: "representatives") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "representativesPK")
            }

            column(name: "full_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "email", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "telephone_number", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1511711648329-3") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_CUSTOMERSNAME_COL", tableName: "customers")
    }

    changeSet(author: "sherlock (generated)", id: "1511711648329-4") {
        addUniqueConstraint(columnNames: "email", constraintName: "UC_REPRESENTATIVESEMAIL_COL", tableName: "representatives")
    }

    changeSet(author: "sherlock (generated)", id: "1511711648329-5") {
        addUniqueConstraint(columnNames: "telephone_number", constraintName: "UC_REPRESENTATIVESTELEPHONE_NUMBER_COL", tableName: "representatives")
    }

    changeSet(author: "sherlock (generated)", id: "1511711648329-6") {
        addForeignKeyConstraint(baseColumnNames: "representative_id", baseTableName: "customers", constraintName: "FKqv7q89bikjxu8jnuth34pguk0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "representatives")
    }
}
