<g:applyLayout name="twoColumns">
    <head>
        <title>Sumario por año</title>
    </head>

    <content tag="main">
        <g:render template="toolbar"/>

        <g:render template="nav"/>

        <table class="table table-hover">
            <caption>${params.year}</caption>

            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <thead>
                <tr>
                    <th>Mes</th>
                    <th>Cantidad de tickets</th>
                </tr>
            </thead>

            <tbody>
                <g:each in="${summaryList}" var="summary">
                    <tr>
                        <td>
                            <g:link
                                resource="employee/ticket"
                                action="summaryInYearAndMonth"
                                params="[employeeId: params.employeeId, year: params.year, month: summary.month]"
                                method="GET">${summary.monthName}</g:link>
                        </td>
                        <td>${summary.count}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </content>
</g:applyLayout>
