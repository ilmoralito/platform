<g:applyLayout name="twoColumns">
    <head>
        <title>Ticket</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td colspan="3" style="border: 0;">DATOS</td>
                </tr>

                <tr>
                    <td style="vertical-align: middle;">Asunto</td>
                    <td colspan="2">${ticket.subject}</td>
                </tr>

                <tr>
                    <td>Solicitado por</td>
                    <td colspan="2">${ticket.employee.fullName}</td>
                </tr>

                <tr>
                    <td>Coordinaciones</td>
                    <td colspan="2">
                        <g:join in="${ticket.applicantCoordinations.coordination.name}"/>
                    </td>
                </tr>

                <tr>
                    <td>Estado</td>
                    <td colspan="2">
                        <platform:ticketStatus status="${ticket.status}"/>
                    </td>
                </tr>

                <tr>
                    <td>Fecha de solicitud</td>
                    <td colspan="2">
                        <g:formatDate date="${ticket.dateCreated}" format="yyyy-MM-dd HH:mm"/>
                    </td>
                </tr>

                <tr>
                    <td>Actualizacion mas reciente</td>
                    <td colspan="2">
                        <g:formatDate date="${ticket.lastUpdated}" format="yyyy-MM-dd HH:mm"/>
                    </td>
                </tr>

                <g:if test="${ticket.status != 'open'}">
                    <tr>
                        <td colspan="3">METADATOS</td>
                    </tr>

                    <tr>
                        <td>Atendido por</td>
                        <td colspan="2">
                            <platform:attendedByEmployeeFullNameList ticket="${ticket}"/>
                        </td>
                    </tr>

                    <tr>
                        <td>Dispositivo</td>
                        <td colspan="2">${ticket.device.name}</td>
                    </tr>
                </g:if>
            </tbody>
        </table>

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
