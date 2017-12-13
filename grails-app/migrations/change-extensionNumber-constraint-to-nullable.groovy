databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1513198939260-1") {
        dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "extension_number", tableName: "coordinations")
    }
}
