<g:applyLayout name="threeColumns">
    <head>
        <title>Sumario de solicitudes por dia</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${results}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Dia</th>
                        <th>Veces requerido</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${results}" var="result">
                        <tr>
                            <td>${result.dayofweek}</td>
                            <td>${result.count}</td>
                        </tr>
                    </g:each>
                    <tr>
                        <td>TOTAL</td>
                        <td>${results.count.sum()}</td>
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
