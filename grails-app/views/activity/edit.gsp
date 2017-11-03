<g:applyLayout name="twoColumns">
    <head>
        <title>Editar actividad</title>
    </head>

    <content tag="main">
        <g:form resource="employee/activity" action="update" params="[id: params.id, employeeId: params.employeeId]" method="PUT" autocomplete="off">
            <g:hiddenField name="_method" value="PUT"/>
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
                <g:link resource="employee/activity" id="${activity.id}" employeeId="${params.employeeId}" method="GET" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <br>
        <g:hasErrors bean="${errors}">
            <g:renderErrors bean="${errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
