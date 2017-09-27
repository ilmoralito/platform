<g:applyLayout name="twoColumns">
    <head>
        <title>Crear actividad</title>
    </head>

    <content tag="main">
        <g:form resource="employee/activity" action="save" params="[employeeId: params.employeeId]" method="POST" autocomplete="off">
            <g:render template="form"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
        </g:form>

        <br>
        <g:hasErrors bean="${errors}">
            <g:renderErrors bean="${errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
