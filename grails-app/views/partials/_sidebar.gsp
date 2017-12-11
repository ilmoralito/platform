<g:set var="springSecurityService" bean="springSecurityService" scope="page"/>

<div class="list-group">
    <g:link controller="dashboard" action="index" method="GET" class="list-group-item ${controllerName == 'dashboard' ? 'active' : ''}">
        Tablero
    </g:link>

    <sec:ifAllGranted roles='ROLE_PROTOCOL'>
        <g:link
            resource="customer"
            action="index"
            method="GET"
            class="list-group-item ${controllerName == 'customer' ? 'active' : ''}">Clientes</g:link>

        <g:link
            resource="color"
            action="index"
            method="GET"
            class="list-group-item ${controllerName == 'color' ? 'active' : ''}">Colores</g:link>

        <g:link
            resource="guest"
            action="index"
            method="GET"
            class="list-group-item ${controllerName == 'guest' ? 'active' : ''}">Visitas</g:link>
    </sec:ifAllGranted>

    <sec:ifAnyGranted roles='ROLE_PROTOCOL, ROLE_ASSISTANT, ROLE_ACADEMIC_COORDINATOR, ROLE_ADMINISTRATIVE_COORDINATOR, ROLE_HEAD_OFFICE_DELEGATE, ROLE_ACADEMIC_DIRECTOR, ROLE_ADMINISTRATIVE_DIRECTOR'>
        <g:link resource="employee/activity" employeeId="${springSecurityService.currentUser.employee.id}" action="index" method="GET" class="list-group-item ${controllerName in ['activity', 'location', 'refreshment'] ? 'active' : ''}">
            Actividades
        </g:link>
    </sec:ifAnyGranted>

    <g:link resource="employee/ticket" employeeId="${springSecurityService.currentUser.employee.id}" action="index" method="GET" class="list-group-item ${controllerName == 'ticket' && !(actionName in ['tickets', 'filterByStatus', 'filterByEmployee', 'filterByDevice', 'filter', 'applyFilter']) ? 'active' : ''}">
        Soporte
    </g:link>
</div>

<sec:ifAllGranted roles='ROLE_ADMIN'>
    <div class="list-group">
        <g:link
            resource="ticket"
            action="tickets"
            class="list-group-item ${controllerName == 'ticket' && actionName in ['tickets', 'filterByStatus', 'filterByEmployee', 'filterByDevice', 'filter', 'applyFilter'] || controllerName == 'task' ? 'active' : ''}">
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

        <g:link resource="employee" action="index" method="GET" class="list-group-item ${controllerName in ['employee', 'employeeCoordination', 'user'] && !(actionName in ['profile', 'password', 'changePassword']) ? 'active' : ''}">
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
