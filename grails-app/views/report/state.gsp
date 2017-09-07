<g:applyLayout name="threeColumns">
    <head>
        <title>Reporte de recursos</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${results}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Mes</th>
                        <th>Abiertos</th>
                        <th>En progreso</th>
                        <th>Cerrados</th>
                        <th>Total</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${results}" var="result">
                        <tr>
                            <td>${result.monthName}</td>
                            <td>${result.open}</td>
                            <td>${result.pending}</td>
                            <td>${result.closed}</td>
                            <td>${result.total}</td>
                        </tr>
                    </g:each>
                    <tr>
                        <td>TOTAL</td>
                        <td>${results.open.sum()}</td>
                        <td>${results.pending.sum()}</td>
                        <td>${results.closed.sum()}</td>
                        <td>${results.total.sum()}</td>
                    </tr>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin resultados que mostrar</p>
        </g:else>
    </content>

    <content tag="right">
        <g:render template="yearListWidget" model="[years: years]"/>
    </content>
</g:applyLayout>
