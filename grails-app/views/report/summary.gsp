<g:applyLayout name="twoColumns">
    <head>
        <title>Resumen</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${results}">
            <table class="table table-hover">
                <caption>${results.size()} resultados</caption>
                <colgroup>
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 40%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 5%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Solicitante</th>
                        <th>Asunto</th>
                        <th>Estado</th>
                        <th>Recurso</th>
                        <th>Tareas</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${results}" var="result">
                        <tr>
                            <td style="vertical-align: middle;">${result.fullName}</td>
                            <td style="vertical-align: middle;">
                                <g:link controller="ticket" action="resume" id="${result.id}">${result.issue}</g:link>
                            </td>
                            <td style="vertical-align: middle;">
                                <platform:ticketStatus status="${result.status}"/>
                            </td>
                            <td style="vertical-align: middle;">${result.device}</td>
                            <td class="text-center" style="vertical-align: middle;">${result.tasks}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>

            <g:link action="export" params="${params}" class="btn btn-default">
                <span class="glyphicon glyphicon-save" aria-hidden="true"></span> CSV
            </g:link>
        </g:if>
        <g:else>
            <p>Sin resultados que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
