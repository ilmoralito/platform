<g:applyLayout name="twoColumns">
    <head>
        <title>Lista de actividades</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <div class="pull-right">
                <g:link class="btn btn-default">Sumario</g:link>
                <g:link resource="employee/activity" action="create" employeeId="${params.employeeId}" method="GET" class="btn btn-primary">
                    Crear actividad
                </g:link>
            </div>
        </div>

        <g:if test="${activityList}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 95%;">
                    <col span="1" style="width: 5%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Actividades</th>
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
                            <td>
                                <activity:state state="${activity.state}"/>
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
