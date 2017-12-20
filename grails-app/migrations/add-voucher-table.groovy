databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1513270074323-1") {
        createTable(tableName: "vouchers") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "vouchersPK")
            }

            column(defaultValue: "El pochote", name: "cafeteria", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "others", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "price", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "breakfast", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "dinner", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "coordination_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "lunch", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "class", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "activity_id", type: "BIGINT")

            column(name: "employee_id", type: "BIGINT")

            column(name: "guest_id", type: "BIGINT")
        }
    }

    changeSet(author: "sherlock (generated)", id: "1513270074323-2") {
        addForeignKeyConstraint(baseColumnNames: "coordination_id", baseTableName: "vouchers", constraintName: "FK381tud7jcfgyyerh1oa302hsy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "coordinations")
    }

    changeSet(author: "sherlock (generated)", id: "1513270074323-3") {
        addForeignKeyConstraint(baseColumnNames: "guest_id", baseTableName: "vouchers", constraintName: "FK9d4jg634od9ipvwj0jip1eccd", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "guests")
    }

    changeSet(author: "sherlock (generated)", id: "1513270074323-4") {
        addForeignKeyConstraint(baseColumnNames: "employee_id", baseTableName: "vouchers", constraintName: "FKhnnk2wokdo16tbluqa8xkixwu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employees")
    }

    changeSet(author: "sherlock (generated)", id: "1513270074323-5") {
        addForeignKeyConstraint(baseColumnNames: "activity_id", baseTableName: "vouchers", constraintName: "FKt89noto7kk5c0mjupdw9r1lt9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "activities")
    }
}
