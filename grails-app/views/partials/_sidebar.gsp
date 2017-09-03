<g:set var="springSecurityService" bean="springSecurityService" scope="page"/>

<div class="list-group">
    <g:link controller="dashboard" action="index" method="GET" class="list-group-item ${controllerName == 'dashboard' ? 'active' : ''}">
        Tablero
    </g:link>

    <g:link resource="employee/ticket" employeeId="${springSecurityService.currentUser.employee.id}" action="index" method="GET" class="list-group-item ${controllerName == 'ticket' && !(actionName in ['tickets', 'filter', 'filterByEmployee', 'filterByDevice']) ? 'active' : ''}">
        Soporte
    </g:link>
</div>

<sec:ifAllGranted roles='ROLE_ADMIN'>
    <div class="list-group">
        <g:link
            resource="ticket"
            action="tickets"
            class="list-group-item ${controllerName == 'ticket' && actionName in ['tickets', 'filter', 'filterByEmployee', 'filterByDevice'] || controllerName == 'task' ? 'active' : ''}">
            <span class="badge">
                <platform:ticketsToAttend/>
            </span>
            Lista de quehaceres
        </g:link>
    </div>
</sec:ifAllGranted>

<sec:ifAllGranted roles='ROLE_ADMIN'>
    <div class="list-group">
        <g:link resource="classroom" action="index" method="GET" class="list-group-item ${controllerName == 'classroom' ? 'active' : ''}">
            Aulas
        </g:link>

        <g:link resource="datashow" action="index" method="GET" class="list-group-item ${controllerName == 'datashow' ? 'active' : ''}" method="GET">
            Cañones
        </g:link>

        <g:link resource="career" action="index" method="GET" class="list-group-item ${controllerName == 'career' ? 'active' : ''}">
            Carreras
        </g:link>

        <g:link controller="coordination" action="index" method="GET" class="list-group-item ${controllerName == 'coordination' ? 'active' : ''}">
            Coordinaciones
        </g:link>

        <g:link resource="employee" action="index" method="GET" class="list-group-item ${controllerName in ['employee', 'employeeCoordination', 'user'] && !(actionName in ['profile', 'password']) ? 'active' : ''}">
            Empleados
        </g:link>

        <g:link resource="holiday" action="index" method="GET" class="list-group-item ${controllerName == 'holiday' ? 'active' : ''}">
            Dias feriados
        </g:link>

        <g:link controller="device" action="index" method="GET" class="list-group-item ${controllerName == 'device' ? 'active' : ''}">
            Recursos
        </g:link>

        <g:link resource="role" action="index" method="GET" class="list-group-item ${controllerName == 'role' ? 'active' : ''}">
            Roles
        </g:link>
    </div>
</sec:ifAllGranted>
