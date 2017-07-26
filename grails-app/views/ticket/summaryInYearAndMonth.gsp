<g:applyLayout name="twoColumns">
    <head>
        <title>Sumario por año y mes</title>
    </head>

    <content tag="main">
        <g:render template="toolbar"/>

        <g:render template="nav"/>

        <table class="table table-hover">
            <caption>${params.month} - ${params.year}</caption>

            <colgroup>
                <col span="1" style="width: 5%;">
                <col span="1" style="width: 95%;">
            </colgroup>

            <thead>
                <tr>
                    <th class="text-center">Dia</th>
                    <th>Asunto</th>
                </tr>
            </thead>

            <tbody>
                <g:each in="${ticketList}" var="ticket">
                    <tr>
                        <td class="text-center">${ticket.dayOfMonth}</td>
                        <td>
                            <g:link
                                resource="employee/ticket"
                                action="show"
                                employeeId="${params.employeeId}" id="${ticket.id}"
                                method="GET">${ticket.subject}</g:link>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </content>
</g:applyLayout>
