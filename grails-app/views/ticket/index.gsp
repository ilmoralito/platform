<g:applyLayout name="twoColumns">
    <head>
        <title>Tickets</title>
    </head>

    <content tag="main">
        <g:render template="toolbar"/>

        <g:render template="nav"/>

        <g:if test="${ticketList}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 83%;">
                    <col span="1" style="width: 12%;">
                </colgroup>

                <thead>
                    <tr>
                        <th class="text-center">#</th>
                        <th>Asunto</th>
                        <th>Actualizacion</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${ticketList}" var="ticket">
                        <tr>
                            <td class="text-center" style="vertical-align: middle;">
                                <g:link resource="employee/ticket" action="show" employeeId="${params.employeeId}" id="${ticket.id}">${ticket.id}</g:link>
                            </td>
                            <td>
                                <g:link resource="employee/ticket" action="show" employeeId="${params.employeeId}" id="${ticket.id}">${ticket.subject}</g:link>
                            </td>
                            <td style="vertical-align: middle;">
                                <g:formatDate date="${ticket.lastUpdated}" format="yyyy-MM-dd HH:mm"/>
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
