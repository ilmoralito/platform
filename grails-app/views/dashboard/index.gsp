<g:applyLayout name="twoColumns">
    <head>
        <title>Tablero</title>
    </head>

    <content tag="main">
        <div class="row">
            <div class="col-md-8">
                <div class="panel panel-default">
                    <div class="panel-heading">Guia Telefónica</div>

                    <table class="table table-hover table-bordered">
                        <colgroup>
                            <col span="1" style="width: 5%;">
                            <col span="1" style="width: 30%;">
                            <col span="1" style="width: 30%;">
                            <col span="1" style="width: 35%;">
                        </colgroup>

                        <thead>
                            <tr>
                                <th>EXT</th>
                                <th>DEPT</th>
                                <th>Coordinador</th>
                                <th>Colaboradores</th>
                            </tr>
                        </thead>

                        <tbody>
                            <g:each in="${phoneBook}" var="pb">
                                <tr>
                                    <td style="vertical-align: middle;">
                                        ${pb.extensionNumber}
                                    </td>
                                    <td style="vertical-align: middle;">
                                        ${pb.coordination}
                                    </td>
                                    <td style="vertical-align: middle;">
                                        ${pb.manager}
                                    </td>
                                    <td style="vertical-align: middle;">
                                        ${pb.assistants}
                                    </td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="col-md-4">
                <sec:ifAllGranted roles='ROLE_ADMIN'>
                    <div class="panel panel-${summaryStatus.open > 5 ? 'warning' : 'default'}">
                        <div class="panel-heading">
                            Lista de quehaceres
                        </div>

                        <table class="table table-hover table-bordered">
                            <tbody>
                                <tr>
                                    <td>Abiertos</td>
                                    <td class="text-center">${summaryStatus.open ?: 0}</td>
                                </tr>

                                <tr>
                                    <td>En progreso</td>
                                    <td class="text-center">${summaryStatus.pending ?: 0}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </sec:ifAllGranted>

                <g:if test="${!params}">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Feriados de <platform:dayOfMonth/>
                        </div>

                        <g:if test="${holidaysInMonth}">
                            <table class="table table-hover table-bordered">
                                <colgroup>
                                    <col span="1" style="width: 5%;">
                                    <col span="1" style="width: 95%;">
                                </colgroup>

                                <thead>
                                    <tr>
                                        <th class="text-center">Fecha</th>
                                        <th>Nombre</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <g:each in="${holidaysInMonth}" var="holiday">
                                        <tr>
                                            <td class="text-center">${holiday.dayOfMonth}</td>
                                            <td>
                                                <g:if test="${holiday.wiki}">
                                                    <g:link url="${holiday.wiki}" target="_blank">${holiday.name}</g:link>
                                                </g:if>
                                                <g:else>
                                                    ${holiday.name}
                                                </g:else>
                                            </td>
                                        </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </g:if>
                        <g:else>
                            <div class="panel-body">
                                Sin resultados en este mes
                            </div>
                        </g:else>

                        <div class="panel-footer">
                            <g:link params="[all: true]">Expandir</g:link>
                        </div>
                    </div>
                </g:if>
                <g:else>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Dias feriados
                        </div>

                        <table class="table table-hover table-bordered">
                            <tbody>
                                <g:each in="${holidayList}" var="list">
                                    <tr>
                                        <td colspan="2">${list.month}</td>
                                    </tr>
                                    <g:each in="${list.holidays}" var="holiday">
                                        <tr>
                                            <td class="text-center">${holiday.dayOfMonth}</td>
                                            <td>
                                                <g:if test="${holiday.wiki}">
                                                    <g:link url="${holiday.wiki}" target="_blank">${holiday.name}</g:link>
                                                </g:if>
                                                <g:else>
                                                    ${holiday.name}
                                                </g:else>
                                            </td>
                                        </tr>
                                    </g:each>
                                </g:each>
                            </tbody>
                        </table>

                        <div class="panel-footer">
                            <g:link>Colapsar</g:link>
                        </div>
                    </div>
                </div>
            </g:else>
        </div>
    </content>
</g:applyLayout>
