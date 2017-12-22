databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: 'setting-default-capacity-if-is-null') {
        sql('UPDATE classrooms SET capacity = 20 WHERE capacity IS NULL')
    }

    changeSet(author: "sherlock (generated)", id: "1513897107730-1") {
        addNotNullConstraint(columnDataType: "int", columnName: "capacity", tableName: "classrooms")
    }
}
