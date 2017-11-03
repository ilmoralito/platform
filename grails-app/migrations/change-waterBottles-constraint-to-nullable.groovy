databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1508685328498-1") {
        dropNotNullConstraint(columnDataType: "int", columnName: "water_bottles", tableName: "locations")
    }
}
