databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1506353280705-1") {
        dropForeignKeyConstraint(baseTableName: "activities", constraintName: "FKak1bh3me1lqlnovapvrtg8qi2")
    }

    changeSet(author: "sherlock (generated)", id: "1506353280705-2") {
        dropColumn(columnName: "created_by_id", tableName: "activities")
    }
}
