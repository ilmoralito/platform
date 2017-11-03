<g:applyLayout name="twoColumns">
    <head>
        <title>Tickets</title>
    </head>

    <content tag="main">
        <g:render template="toolbar"/>

        <g:render template="nav"/>

        <g:if test="${ticketList}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Asunto</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${ticketList}" var="ticket">
                        <tr>
                            <td>
                                <g:link resource="employee/ticket" action="show" employeeId="${params.employeeId}" id="${ticket.id}">${ticket.subject}</g:link>
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
