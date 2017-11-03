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
                        <div class="label label-info">
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
                    <td>Organizado por</td>
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

        %{--<g:if test="${activity.state == 'create'}">--}%
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

            <a class="btn btn-warning" onclick="document.querySelector('#notifyForm').submit()">Notificar</a>
        %{--</g:if>--}%

        <g:link
            resource="employee/activity/location"
            params="[activityId: activity.id, employeeId: params.employeeId]"
            method="GET"
            class="btn btn-default">Ubicaciones</g:link>

        <g:form
            name="deleteForm"
            resource="employee/activity"
            params="[id: activity.id, employeeId: params.employeeId]"
            action="delete"
            method="DELETE">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>

        <g:form
            name="notifyForm"
            resource="employee/activity"
            action="sendNotification"
            params="[employeeId: params.employeeId, activityId: activity.id]"
            method="PUT">
            <g:hiddenField name="_method" value="PUT"/>
        </g:form>
    </content>
</g:applyLayout>
