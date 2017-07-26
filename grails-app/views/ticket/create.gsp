<g:applyLayout name="twoColumns">
    <head>
        <title>Crear ticket</title>
    </head>

    <content tag="main">
        <g:form resource="employee/ticket" params="[employeeId: params.employeeId]" action="save" method="POST" autocomplete="off">
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Agregar" class="btn btn-primary"/>
                <g:link resource="employee/ticket" action="index" employeeId="$params.employeeId" method="GET" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${ticket}">
            <g:renderErrors bean="${ticket}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
