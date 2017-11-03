databaseChangeLog = {
    include file: 'init.groovy'
    include file: 'create-activity-table-and-observation-table.groovy'
    include file: 'change-authorizedBy-and-authorizationDate-constraint-to-nullable.groovy'
    include file: 'remove-createdBy-from-activities-table.groovy'
    include file: 'create-colors-table.groovy'
    include file: 'create-coordination-colors-table.groovy'
    include file: 'create-tables-locations-refreshments-tablecloths.groovy'
    include file: 'add-power-outlet-number-field-to-classrooms-table.groovy'
    include file: 'add-projector-table-field.groovy'
    include file: 'change-waterBottles-constraint-to-nullable.groovy'
    include file: 'add-national-anthem-triumphal-anthem-university-anthem.groovy'
}
