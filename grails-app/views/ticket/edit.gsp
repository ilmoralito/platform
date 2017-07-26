<g:applyLayout name="twoColumns">
    <head>
        <title>Editar ticket</title>
    </head>

    <content tag="main">
        <g:form resource="employee/ticket" action="update" params="[employeeId: params.employeeId, id: ticket.id]" method="PUT" autocomplete="off">
            <g:hiddenField name="_method" value="PUT"/>
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
                <g:link resource="employee/ticket" action="show" employeeId="${params.employeeId}" id="${ticket.id}" method="GET" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${ticket}">
            <g:renderErrors bean="${ticket}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
