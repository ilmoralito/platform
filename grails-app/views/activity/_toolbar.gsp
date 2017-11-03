<div class="clearfix">
    <div class="pull-right">
        <sec:ifAnyGranted roles='ROLE_COORDINATOR, ROLE_ACADEMIC_COORDINATOR, ROLE_ADMINISTRATIVE_COORDINATOR'>
            <g:link
                resource="employee/activity"
                action="requiringAttention"
                params="[employeeId: params.employeeId]"
                class="btn btn-default ${actionName == 'requiringAttention' ? 'active' : ''}">
                Notificaciones
            </g:link>
        </sec:ifAnyGranted>

        <div class="btn-group">
            <button type="button" class="btn btn-default">Filtrar por estado</button>
                <button
                    type="button"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                    class="btn btn-default dropdown-toggle">
                <span class="caret"></span>
                <span class="sr-only">Toggle Dropdown</span>
            </button>

            <ul class="dropdown-menu">
                <g:each in="${stateList}" var="state">
                    <li class="${params?.state == state ? 'active' : ''}">
                        <g:link
                            resource="employee/activity"
                            action="filter"
                            params="[employeeId: params.employeeId, state: state]">
                            <activity:state currentState="${state}"/>
                        </g:link>
                    </li>
                </g:each>
            </ul>
        </div>

        <g:link
            resource="employee/activity"
            action="create"
            employeeId="${params.employeeId}"
            method="GET"
            class="btn btn-primary">
            Crear actividad
        </g:link>
    </div>
</div>
