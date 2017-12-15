databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1513372951675-1") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_COFFEESHOPSNAME_COL", tableName: "coffeeshops")
    }
}
