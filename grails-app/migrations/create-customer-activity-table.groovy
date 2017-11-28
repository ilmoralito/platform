databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1511803852191-1") {
        addColumn(tableName: "activities") {
            column(name: "customer_id", type: "bigint")
        }
    }

    changeSet(author: "sherlock (generated)", id: "1511803852191-2") {
        addForeignKeyConstraint(baseColumnNames: "customer_id", baseTableName: "activities", constraintName: "FKeds7272usjbk8laufntn26ooc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "customers")
    }
}
