<g:applyLayout name="twoColumns">
    <head>
        <title>Lista de ubicaciones</title>
    </head>

    <content tag="main">
        <g:if test="${locationList}">
            <div class="row">
                <div class="col-md-9">
                    <h4>
                        <g:link resource="employee/activity" id="${activity.id}" employeeId="${params.employeeId}" method="GET">
                            ${activity.name}
                        </g:link>
                    </h4>
                </div>

                <div class="col-md-3">
                    <activity:isValid activityId="${activity.id}">
                        <g:link
                            resource="employee/activity/location"
                            action="create"
                            params="[employeeId: params.employeeId, activityId: params.activityId]"
                            method="GET"
                            class="btn btn-primary pull-right">
                            Agregar ubicacion
                        </g:link>
                    </activity:isValid>
                </div>
            </div>

            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 58%;">
                    <col span="1" style="width: 17%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 5%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Lugar</th>
                        <th>Fecha y hora de inicio</th>
                        <th>Hora de clausura</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${locationList}" var="location">
                        <tr>
                            <td>
                                <g:link
                                    resource="employee/activity/location"
                                    employeeId="${params.employeeId}" activityId="${params.activityId}" id="${location.id}"
                                    method="GET">
                                    ${location.place.code} ${location.place.name}
                                </g:link>
                            </td>
                            <td>
                                <g:formatDate date="${location.startDateAndTime}" format="yyyy-MM-dd hh:00"/>
                            </td>
                            <td>
                                <g:formatDate date="${location.endDateAndTime}" format="hh:00"/>
                            </td>
                            <td>
                                <activity:isValid activityId="${activity.id}">
                                    <g:link
                                        resource="employee/activity/location"
                                        action="clone"
                                        params="[employeeId: params.employeeId, activityId: params.activityId, locationId: location.id]"
                                        method="GET">
                                        Duplicar
                                    </g:link>
                                </activity:isValid>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin ubicaciones asignadas a esta actividad</p>
            <g:link
                resource="employee/activity/location"
                action="create"
                params="[employeeId: params.employeeId, activityId: params.activityId]"
                method="GET"
                class="btn btn-primary">
                Agregar ubicacion
            </g:link>
        </g:else>
    </content>
</g:applyLayout>
