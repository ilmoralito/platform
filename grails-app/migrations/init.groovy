databaseChangeLog = {

    changeSet(author: "sherlock (generated)", id: "1502719900994-1") {
        createTable(tableName: "applicant_coordination") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "applicant_coordinationPK")
            }

            column(name: "ticket_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "coordination_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-2") {
        createTable(tableName: "attended_by") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "attended_byPK")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "ticket_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "employee_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-3") {
        createTable(tableName: "bookmarks") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "bookmarksPK")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "employee_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "class", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "ticket_id", type: "BIGINT")
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-4") {
        createTable(tableName: "careers") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "careersPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-5") {
        createTable(tableName: "classrooms") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "classroomsPK")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)")

            column(name: "capacity", type: "INT")

            column(defaultValueComputed: "0", name: "wifi", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(defaultValueBoolean: "false", name: "air_conditioned", type: "BOOLEAN") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-6") {
        createTable(tableName: "coordinations") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "coordinationsPK")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "extension_number", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "copy_fee", type: "INT")

            column(name: "area", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "acronym", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-7") {
        createTable(tableName: "datashows") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "datashowsPK")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "code", type: "INT") {
                constraints(nullable: "false")
            }

            column(defaultValueComputed: "0", name: "ethernet", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(defaultValueComputed: "0", name: "wifi", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "trademark", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(defaultValueComputed: "1", name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "model", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "serial_number", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(defaultValueComputed: "0", name: "hdmi", type: "BOOLEAN") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-8") {
        createTable(tableName: "devices") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "devicesPK")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-9") {
        createTable(tableName: "employee_coordinations") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "employee_coordinationsPK")
            }

            column(name: "job_title", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "position", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "employee_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "coordination_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-10") {
        createTable(tableName: "employees") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "employeesPK")
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

            column(defaultValue: "permanent", name: "contract", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT")

            column(name: "identity_card", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(defaultValueComputed: "1", name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-11") {
        createTable(tableName: "holidays") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "holidaysPK")
            }

            column(name: "date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "wiki", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-12") {
        createTable(tableName: "roles") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "rolesPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-13") {
        createTable(tableName: "tasks") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "tasksPK")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "created_by_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "ticket_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "CLOB") {
                constraints(nullable: "false")
            }

            column(defaultValue: "default", name: "state", type: "VARCHAR(7)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-14") {
        createTable(tableName: "tickets") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "ticketsPK")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(defaultValueComputed: "0", name: "scheduled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "device_id", type: "BIGINT")

            column(defaultValue: "open", name: "status", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "employee_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "subject", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-15") {
        createTable(tableName: "user_role") {
            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-16") {
        createTable(tableName: "users") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "usersPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "email", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-17") {
        addPrimaryKey(columnNames: "user_id, role_id", constraintName: "user_rolePK", tableName: "user_role")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-18") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_CAREERSNAME_COL", tableName: "careers")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-19") {
        addUniqueConstraint(columnNames: "code", constraintName: "UC_CLASSROOMSCODE_COL", tableName: "classrooms")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-20") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_COORDINATIONSNAME_COL", tableName: "coordinations")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-21") {
        addUniqueConstraint(columnNames: "code", constraintName: "UC_DATASHOWSCODE_COL", tableName: "datashows")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-22") {
        addUniqueConstraint(columnNames: "serial_number", constraintName: "UC_DATASHOWSSERIAL_NUMBER_COL", tableName: "datashows")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-23") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_DEVICESNAME_COL", tableName: "devices")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-24") {
        addUniqueConstraint(columnNames: "identity_card", constraintName: "UC_EMPLOYEESIDENTITY_CARD_COL", tableName: "employees")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-25") {
        addUniqueConstraint(columnNames: "authority", constraintName: "UC_ROLESAUTHORITY_COL", tableName: "roles")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-26") {
        addUniqueConstraint(columnNames: "email", constraintName: "UC_USERSEMAIL_COL", tableName: "users")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-27") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "employees", constraintName: "FK69x3vjuy1t5p18a5llb8h2fjx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "users")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-28") {
        addForeignKeyConstraint(baseColumnNames: "employee_id", baseTableName: "bookmarks", constraintName: "FK923b1wg3rrps7yrhmkp28ilp8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employees")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-29") {
        addForeignKeyConstraint(baseColumnNames: "ticket_id", baseTableName: "applicant_coordination", constraintName: "FKa52jwt9c16ej49vyhrysumif8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tickets")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-30") {
        addForeignKeyConstraint(baseColumnNames: "coordination_id", baseTableName: "employee_coordinations", constraintName: "FKb2e6mkgrqrjxkqtpugnigi9ry", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "coordinations")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-31") {
        addForeignKeyConstraint(baseColumnNames: "employee_id", baseTableName: "employee_coordinations", constraintName: "FKenfby6ii5uhm3ulr8kt8xxxf3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employees")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-32") {
        addForeignKeyConstraint(baseColumnNames: "ticket_id", baseTableName: "tasks", constraintName: "FKhhv2ohji3lbs6wtha5fisk7l4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tickets")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-33") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FKj345gk1bovqvfame88rcx7yyx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "users")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-34") {
        addForeignKeyConstraint(baseColumnNames: "coordination_id", baseTableName: "applicant_coordination", constraintName: "FKko9vvvf01lt40a5wn8m2jm8wm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "coordinations")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-35") {
        addForeignKeyConstraint(baseColumnNames: "created_by_id", baseTableName: "tasks", constraintName: "FKmeg3m9hk7eyq7u5kpot87f9ey", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "users")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-36") {
        addForeignKeyConstraint(baseColumnNames: "ticket_id", baseTableName: "bookmarks", constraintName: "FKn0qdbfh8nmt1tbo7qdamyf9gj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tickets")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-37") {
        addForeignKeyConstraint(baseColumnNames: "employee_id", baseTableName: "tickets", constraintName: "FKq2vegfec7xrs4oeytnnp5yluo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employees")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-38") {
        addForeignKeyConstraint(baseColumnNames: "ticket_id", baseTableName: "attended_by", constraintName: "FKqfmd5hk3on6tqh71pjonrlv99", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tickets")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-39") {
        addForeignKeyConstraint(baseColumnNames: "employee_id", baseTableName: "attended_by", constraintName: "FKqyy72hp3drp2rgmhb8vpr0my9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employees")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-40") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FKt7e7djp752sqn6w22i6ocqy6q", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "roles")
    }

    changeSet(author: "sherlock (generated)", id: "1502719900994-41") {
        addForeignKeyConstraint(baseColumnNames: "device_id", baseTableName: "tickets", constraintName: "FKtey5tii21tr6jti2at83clysr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "devices")
    }
}
