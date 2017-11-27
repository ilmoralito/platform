databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1511715793960-1") {
        addColumn(tableName: "representatives") {
            column(name: "identification_card", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1511715793960-2") {
        addUniqueConstraint(columnNames: "identification_card", constraintName: "UC_REPRESENTATIVESIDENTIFICATION_CARD_COL", tableName: "representatives")
    }
}
