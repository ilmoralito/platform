databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1508686515661-1") {
        addColumn(tableName: "locations") {
            column(name: "national_anthem", type: "bit") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1508686515661-2") {
        addColumn(tableName: "locations") {
            column(name: "triumphal_anthem", type: "bit") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1508686515661-3") {
        addColumn(tableName: "locations") {
            column(name: "university_anthem", type: "bit") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1508686515661-4") {
        dropColumn(columnName: "anthem", tableName: "locations")
    }
}
