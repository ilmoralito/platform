<g:applyLayout name="twoColumns">
    <head>
        <title>Ticket</title>
    </head>

    <content tag="main">
        <g:render template="ticket" model="[ticket: ticket]"/>

        <g:if test="${ticket.status == 'open'}">
            <g:link resource="employee/ticket" action="edit" employeeId="${params.employeeId}" id="${ticket.id}" class="btn btn-primary">Editar</g:link>

            <a href="#" class="btn btn-warning" onclick="if (confirm('¿Seguro?')) { document.getElementById('deleteForm').submit(); } else { return false; }">Eliminar</a>
            <g:form name="deleteForm" resource="employee/ticket" action="delete" params="[employeeId: params.employeeId, id: ticket.id]" method="DELETE" class="hide">
                <g:hiddenField name="_method" value="DELETE"/>
            </g:form>
        </g:if>

        <g:link uri="${request.getHeader('referer')}" method="GET" class="btn btn-default">Regresar</g:link>
    </content>
</g:applyLayout>
