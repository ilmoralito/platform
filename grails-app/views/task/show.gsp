<g:applyLayout name="twoColumns">
    <head>
        <title>Tarea</title>
    </head>

    <content tag="main">
        <g:if test="${task.ticket.status != 'closed'}">
            <g:link resource="ticket/task" action="edit" ticketId="${params.ticketId}" id="${task.id}" class="btn btn-primary">Editar</g:link>

            <a href="#" onclick="if (confirm('¿Seguro?')) { document.getElementById('deleteForm').submit(); } else { return false; }" class="btn btn-warning">Eliminar</a>
            <g:form name="deleteForm" resource="ticket/task" action="delete" params="[id: task.id, ticketId: task.ticket.id]" method="DELETE" class="hide">
                <g:hiddenField name="_method" value="DELETE"/>
            </g:form>
        </g:if>
        <g:link resource="ticket/task" action="index" params="[ticketId: params.ticketId]" class="btn btn-default">Regresar</g:link>

        <table class="table table-hover" style="margin-top: 15px;">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td>Creado por</td>
                    <td>${task.createdBy.employee.fullName}</td>
                </tr>

                <tr>
                    <td>Fecha de creacion</td>
                    <td>
                        <g:formatDate date="${task.dateCreated}" form="yyyy-MM-mm HH:mm"/>
                    </td>
                </tr>

                <tr>
                    <td>Actualizacion mas reciente</td>
                    <td>
                        <g:formatDate date="${task.lastUpdated}" form="yyyy-MM-mm HH:mm"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">Descripcion</td>
                </tr>

                <tr>
                    <td colspan="2">
                        <markdown:renderHtml>${task.description}</markdown:renderHtml>
                    </td>
                </tr>
            </tbody>
        </table>
    </content>
</g:applyLayout>
