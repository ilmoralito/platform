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
    include file: 'add-microphone-field-to-location.groovy'
    include file: 'add-customer-and-representative-tables.groovy'
    include file: 'add-identification-card-to-representative.groovy'
    include file: 'create-customer-activity-table.groovy'
    include file: 'add-academic-title-to-representative.groovy'
    include file: 'add-guests-table.groovy'
    include file: 'add-coordinations_guests-table.groovy'
    include file: 'change-extensionNumber-constraint-to-nullable.groovy'
}
