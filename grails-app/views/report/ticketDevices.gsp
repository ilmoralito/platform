<g:applyLayout name="oneColumnSmall">
    <head>
        <title>Reporte de estado de tickets por coordinacion</title>
    </head>

    <content tag="main">
        <g:link uri="${request.getHeader('referer')}" class="btn btn-default" style="margin-bottom: 10px;">Regresar</g:link>

        <g:if test="${results}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Recurso</th>
                        <th>Cantidad</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${results}" var="result">
                        <tr>
                            <td>${result.name}</td>
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
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
