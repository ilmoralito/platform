databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1513003771989-1") {
        createTable(tableName: "guests") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "guestsPK")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "full_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }
}
