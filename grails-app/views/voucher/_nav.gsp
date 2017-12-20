<ul class="nav nav-tabs">
    <li role="presentation" class="${!params.type || params.type == 'employee' ? 'active' : ''}">
        <g:link
            resource="employee/activity/voucher"
            action="index"
            params="[employeeId: params.employeeId, activityId: params.activityId, type: 'employee']"
            method="GET">Empleados</g:link>
    </li>

    <li role="presentation" class="${params.type == 'guest' ? 'active' : ''}">
        <g:link
            resource="employee/activity/voucher"
            action="index"
            params="[employeeId: params.employeeId, activityId: params.activityId, type: 'guest']"
            method="GET">Visitas</g:link>
    </li>
</ul>