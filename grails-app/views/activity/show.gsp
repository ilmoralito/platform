<g:applyLayout name="twoColumns">
    <head>
        <title>Actividad</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <col width="25%">
            <col width="75%">

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
                    <td>${activity.employee.fullName}</td>
                </tr>

                <tr>
                    <td>Organizador</td>
                    <td>${activity.organizedBy.name}</td>
                </tr>

                <g:if test="${activity.instanceOf(ni.edu.ucc.leon.CustomerActivity)}">
                    <tr>
                        <td>Cliente</td>
                        <td>${activity.customer.name}</td>
                    </tr>
                </g:if>

                <g:if test="${activity.employeeVouchers || activity.guestVouchers}">
                    <tr>
                        <td>Vales</td>
                        <td>${activity.employeeVouchers.size() + activity.guestVouchers.size()}</td>
                    </tr>
                </g:if>

                <tr>
                    <td colspan="2">META</td>
                </tr>

                <tr>
                    <td>Creacion</td>
                    <td>
                        <g:formatDate date="${activity.dateCreated}" format="yyyy-MM-dd HH:mm"/>
                    </td>
                </tr>

                <g:if test="${activity.instanceOf(AcademicActivity) && activity.confirmedBy}">
                    <tr>
                        <td>Confirmacion</td>
                        <td>
                            <g:formatDate date="${activity.confirmationDate}" format="yyyy-MM-dd HH:mm"/>
                        </td>
                    </tr>
                </g:if>

                <g:if test="${activity.instanceOf(AcademicActivity) && activity.approvedBy}">
                    <tr>
                        <td>Aprobacion</td>
                        <td>
                            <g:formatDate date="${activity.approvalDate}" format="yyyy-MM-dd HH:mm"/>
                        </td>
                    </tr>
                </g:if>

                <g:if test="${activity.authorizedBy}">
                    <tr>
                        <td>Autorizacion</td>
                        <td>
                            <g:formatDate date="${activity.authorizationDate}" format="yyyy-MM-dd HH:mm"/>
                        </td>
                    </tr>
                </g:if>
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
                <a class="btn btn-warning" onclick="document.querySelector('#notifyForm').submit()">
                    <span class="glyphicon glyphicon-send" aria-hidden="true"></span> Notificar</a>
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

        <g:link
            resource="employee/activity/location"
            action="${activity.locations ? 'index' : 'create'}"
            params="[employeeId: params.employeeId, activityId: activity.id]"
            method="GET"
            class="btn btn-default">
                <span class="glyphicon glyphicon-pushpin" aria-hidden="true"></span> ${activity.locations ? 'Ubicaciones' : 'Agregar ubicacion'}</g:link>

        <g:if test="${(activity.state == 'created' && activity.locations) || (activity.state != 'created' && activity.locations && (activity.employeeVouchers || activity.guestVouchers))}">
            <g:link
                resource="employee/activity/voucher"
                action="index"
                params="[employeeId: params.employeeId, activityId: activity.id, type: 'employee']"
                method="GET"
                class="btn btn-default">
                <span class="glyphicon glyphicon-tag" aria-hidden="true"></span> Vales</g:link>
        </g:if>

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
