databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1513521405439-1") {
        addColumn(tableName: "vouchers") {
            column(name: "coffee_shop_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1513521405439-2") {
        addForeignKeyConstraint(baseColumnNames: "coffee_shop_id", baseTableName: "vouchers", constraintName: "FKl4asees9v0mc3jf7snabo9gvd", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "coffeeshops")
    }

    changeSet(author: "sherlock (generated)", id: "1513521405439-3") {
        dropColumn(columnName: "cafeteria", tableName: "vouchers")
    }
}
