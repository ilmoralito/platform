databaseChangeLog = {
    include file: 'init.groovy'
    include file: 'create-activity-table-and-observation-table.groovy'
    include file: 'change-authorizedBy-and-authorizationDate-constraint-to-nullable.groovy'
    include file: 'remove-createdBy-from-activities-table.groovy'
    include file: 'create-colors-table.groovy'
    include file: 'create-coordination-colors-table.groovy'
}
