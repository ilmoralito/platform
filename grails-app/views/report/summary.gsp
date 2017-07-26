<g:applyLayout name="oneColumnSmall">
    <head>
        <title>Reporte de tickets</title>
    </head>

    <content tag="main">
        <g:if test="${results}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 40%;">
                    <col span="1" style="width: 40%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Año</th>
                        <th>Cantidad</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${results}" var="result">
                        <tr>
                            <td>
                                <g:link controller="report" action="summaryInYear" params="[year: result.year]">${result.year}</g:link>
                            </td>
                            <td>${result.count}</td>
                            <td>
                                <g:link controller="report" action="ticketStatusInYear" params="[year: result.year]">Reporte de estado</g:link>
                            </td>
                            <td>
                                <g:link controller="report" action="ticketDevicesInYear" params="[year: result.year]">Reporte de recurso</g:link>
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
