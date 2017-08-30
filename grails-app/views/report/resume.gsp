<g:applyLayout name="threeColumns">
    <head>
        <title>Reporte</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${results}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 75%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Mes</th>
                        <th>Numero de asistencias en el mes</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${results}" var="result">
                        <tr>
                            <td>
                                <g:link
                                    action="${params.year ? 'resumeInYearAndMonth' : 'resumeInMonth'}"
                                    params="${params.year ? [year: params.year, monthName: result.monthName] : [monthName: result.monthName]}">
                                    ${result.monthName}
                                </g:link>
                            </td>
                            <td>${result.count}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin resultados que mostrar</p>
        </g:else>
    </content>

    <content tag="right">
        <g:render
            template="yearListWidget"
            model="[yearListWidget: yearListWidget]"/>
    </content>
</g:applyLayout>
