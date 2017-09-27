databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1506351238391-1") {
        dropNotNullConstraint(columnDataType: "datetime", columnName: "authorization_date", tableName: "activities")
    }

    changeSet(author: "sherlock (generated)", id: "1506351238391-2") {
        dropNotNullConstraint(columnDataType: "bigint", columnName: "authorized_by_id", tableName: "activities")
    }
}
