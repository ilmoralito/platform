<g:applyLayout name="twoColumns">
    <head>
        <title>Editar Coordinacion de empleado</title>
    </head>

    <content tag="main">
        <g:form resource="employee/employeeCoordination" action="update" params="[id: employeeCoordination.id, employeeId: employeeCoordination.employee.id]" method="PUT" autocomplete="off">
            <g:hiddenField name="_method" value="PUT"/>

            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="sendTask" value="Actualizar" class="btn btn-primary"/>
                <g:link resource="employee/employeeCoordination" action="index" params="[employeeId: employeeCoordination.employee.id]" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${employeeCoordination}">
            <g:renderErrors bean="${employeeCoordination}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
