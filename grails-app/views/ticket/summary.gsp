<g:applyLayout name="twoColumns">
    <head>
        <title>Sumario</title>
    </head>

    <content tag="main">
        <g:render template="toolbar"/>

        <g:render template="nav"/>

        <g:if test="${yearList}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Años</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${yearList}" var="year">
                        <tr>
                            <td>
                                <g:link resource="employee/ticket" action="summaryInYear" params="[year: year]" employeeId="${params.employeeId}" params="[year: year]">${year}</g:link>
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
