<g:applyLayout name="twoColumns">
    <head>
        <title>Lista de actividades</title>
    </head>

    <content tag="main">
        <g:render template="toolbar"/>

        <g:if test="${activityList}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 89%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 1%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Actividades</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${activityList}" var="activity">
                        <tr>
                            <td>
                                <g:link resource="employee/activity" action="show" id="${activity.id}" employeeId="${params.employeeId}"  method="GET">
                                    ${activity.name}
                                </g:link>
                            </td>
                            <td class="text-center">
                                <g:link resource="employee/activity/location" params="[activityId: activity.id, employeeId: params.employeeId]" method="GET">
                                    Ubicaciones
                                </g:link>
                            </td>
                            <td>
                                <span class="${activity.locations.size() ? 'label label-primary' : ''}">
                                    ${activity.locations.size() ?: ''}
                                </span>
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
