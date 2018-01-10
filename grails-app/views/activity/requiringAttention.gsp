<g:applyLayout name="twoColumns">
    <head>
        <title>Lista de actividades</title>
    </head>

    <content tag="main">
        <g:render template="toolbar"/>

        <g:if test="${activityListByOrganizer}">
            <table class="table table-hover table-bordered">
                <col width="85%">
                <col width="10%">
                <col width="5%">

                <thead>
                    <tr>
                        <th>Actividades que requieren atencion</th>
                        <th class="text-center">Ubicaciones</th>
                        <th class="text-center">Vales</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${activityListByOrganizer}" var="organizer">
                        <tr>
                            <td colspan="3">${organizer.organizer}</td>
                        </tr>
                        <g:each in="${organizer.activities}" var="activity">
                            <tr>
                                <td>
                                    <g:link
                                        resource="employee/activity"
                                        action="show"
                                        params="[employeeId: params.employeeId, id: activity.id]"
                                        method="GET">
                                        ${activity.name}
                                    </g:link>
                                </td>
                                <td class="text-center">
                                    ${activity.locations}
                                </td>
                                <td class="text-center">
                                    ${activity.vouchers}
                                </td>
                            </tr>
                        </g:each>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
