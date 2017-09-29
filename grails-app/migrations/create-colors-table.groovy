databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1506628959313-1") {
        createTable(tableName: "colors") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "colorsPK")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1506628959313-4") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_COLORSNAME_COL", tableName: "colors")
    }
}
