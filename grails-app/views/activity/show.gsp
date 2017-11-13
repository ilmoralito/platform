<g:applyLayout name="twoColumns">
    <head>
        <title>Actividad</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td>Nombre</td>
                    <td>${activity.name}</td>
                </tr>
                <tr>
                    <td>Estado</td>
                    <td>
                        <div class="label ${activity.state == 'attended' ? 'label-success' : activity.state == 'canceled' ? 'label-danger' : 'label-info'}">
                            <activity:state currentState="${activity.state}"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Creado por</td>
                    <td>
                        ${activity.employee.fullName}
                    </td>
                </tr>
                <tr>
                    <td>Organizador</td>
                    <td>${activity.organizedBy.name}</td>
                </tr>
                <tr>
                    <td>Fecha y hora de creacion</td>
                    <td>
                        <g:formatDate date="${activity.dateCreated}" format="yyyy-MM-dd HH:mm"/>
                    </td>
                </tr>
            </tbody>
        </table>

        <activity:isValid activityId="${activity.id}">
            <g:link
                resource="employee/activity"
                action="edit"
                id="${activity.id}"
                employeeId="${params.employeeId}"
                method="GET"
                class="btn btn-primary">Editar</g:link>

            <a
                href="#"
                class="btn btn-danger"
                onclick="if (confirm('¿Seguro?')) document.querySelector('#deleteForm').submit()">Eliminar</a>

            <g:if test="${activity.locations}">
                <a class="btn btn-warning" onclick="document.querySelector('#notifyForm').submit()">Notificar</a>
            </g:if>
        </activity:isValid>

        <sec:ifAnyGranted roles='ROLE_PROTOCOL'>
            <g:if test="${activity.state == 'authorized'}">
                <div class="btn-group">
                    <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Marcar actividad <span class="caret"></span>
                    </button>

                    <ul class="dropdown-menu">
                        <li>
                            <a href="#" onclick="document.querySelector('#notifyAttendedForm').submit()">Atendido</a>
                        </li>
                        <li>
                            <a href="#" onclick="document.querySelector('#notifyCanceledForm').submit()">Cancelado</a>
                        </li>
                    </ul>
                </div>
            </g:if>
        </sec:ifAnyGranted>

        <g:if test="${activity.locations}">
            <g:link
                resource="employee/activity/location"
                params="[activityId: activity.id, employeeId: params.employeeId]"
                method="GET"
                class="btn btn-default">Ubicaciones</g:link>
        </g:if>
        <g:else>
            <g:link
                resource="employee/activity/location"
                action="create"
                params="[employeeId: params.employeeId, activityId: activity.id]"
                method="GET"
                class="btn btn-default">Agregar ubicacion</g:link>
        </g:else>

        <g:form name="deleteForm"
            resource="employee/activity"
            params="[id: activity.id, employeeId: params.employeeId]"
            action="delete"
            method="DELETE">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>

        <g:form name="notifyForm"
            resource="employee/activity"
            action="sendNotification"
            params="[employeeId: params.employeeId, activityId: activity.id]"
            method="PUT">
                <g:hiddenField name="_method" value="PUT"/>
        </g:form>

        <g:form name="notifyAttendedForm"
            resource="employee/activity"
            action="sendNotification"
            params="[employeeId: params.employeeId, activityId: activity.id, state: 'attended']"
            method="PUT">
                <g:hiddenField name="_method" value="PUT"/>
        </g:form>

        <g:form name="notifyCanceledForm"
            resource="employee/activity"
            action="sendNotification"
            params="[employeeId: params.employeeId, activityId: activity.id, state: 'canceled']"
            method="PUT">
                <g:hiddenField name="_method" value="PUT"/>
        </g:form>
    </content>
</g:applyLayout>
