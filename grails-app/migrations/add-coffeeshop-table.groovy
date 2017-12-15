databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1513366157067-1") {
        createTable(tableName: "coffee_shop") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "coffee_shopPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1513366157067-2") {
        createTable(tableName: "coffeeshops") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "coffeeshopsPK")
            }

            column(name: "others", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "breakfast", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "dinner", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "lunch", type: "INT") {
                constraints(nullable: "false")
            }
        }
    }
}
