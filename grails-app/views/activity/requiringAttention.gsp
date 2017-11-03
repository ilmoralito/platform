<g:applyLayout name="twoColumns">
    <head>
        <title>Lista de actividades</title>
    </head>

    <content tag="main">
        <g:render template="toolbar"/>

        <g:if test="${activityListByOrganizer}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Actividades que requieren atencion</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${activityListByOrganizer}" var="organizer">
                        <tr>
                            <td>${organizer.organizer}</td>
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
                                </tr>
                            </g:each>
                        <tr>
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
