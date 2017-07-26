<g:applyLayout name="twoColumns">
    <head>
        <title>Dias feriados</title>
    </head>

    <content tag="main">
        <g:link resource="holiday" action="create" method="GET" class="btn btn-primary pull-right">Crear dia feriado</g:link>

        <g:if test="${holidayList}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Lista de dias feriados</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${holidayList}" var="holiday">
                        <tr>
                            <td>
                                <g:link resource="${holiday}" action="show">${holiday.name}</g:link>
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
