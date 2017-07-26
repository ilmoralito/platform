<g:applyLayout name="oneColumnSmall">
    <head>
        <title>Marcadores</title>
    </head>

    <content tag="main">
        <g:link uri="/tickets" class="btn btn-default" style="margin-bottom: 15px;">Lista de tickets</g:link>

        <g:if test="${ticketBookmarkList}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 75%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 5%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Asunto</th>
                        <th>Solicitante</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${ticketBookmarkList}" var="bookmark">
                        <tr>
                            <td>
                                <g:link resource="ticket/task" action="index" ticketId="${bookmark.ticket.id}" method="GET">${bookmark.ticket.subject}</g:link>
                            </td>
                            <td>${bookmark.employee.fullName}</td>
                            <td>
                                <a href="" class="deleteBookmarkTrigger">Eliminar</a>
                                <g:form resource="employee/bookmark" action="delete" params="[employeeId: params.employeeId, id: bookmark.id]" method="DELETE" class="hide">
                                    <g:hiddenField name="_method" value="DELETE"/>
                                </g:form>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
