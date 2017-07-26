<g:applyLayout name="oneColumnSmall">
    <head>
        <title>Reporte de tickets por anio</title>
    </head>

    <content tag="main">
        <g:link controller="report" action="summary" class="btn btn-default" style="margin-bottom: 15px;">Regresar</g:link>

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
                        <th>Mes</th>
                        <th>Cantidad</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${results}" var="result">
                        <tr>
                            <td>${result.monthName}</td>
                            <td>${result.count}</td>
                            <td>
                                <g:link controller="report" action="ticketStatusInYearAndMonth" params="[year: params.year, month: result.month]">Reporte de estado</g:link>
                            </td>
                            <td>
                                <g:link controller="report" action="ticketDevicesInYearAndMonth" params="[year: params.year, month: result.month]">Reporte de recurso</g:link>
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
