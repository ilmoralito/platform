<g:applyLayout name="threeColumns">
    <head>
        <title>Tareas</title>
    </head>

    <content tag="main">
        <g:if test="${ticket.status != 'closed'}">
            <g:form resource="ticket/task" action="save" params="[ticketId: params.ticketId]" method="POST" autocomplete="off">
                <g:render template="/task/form"/>

                <div class="form-group">
                    <g:submitButton name="sendTask" value="Agregar" class="btn btn-primary"/>
                </div>
            </g:form>
        </g:if>

        <g:if test="${taskList}">
            <g:each in="${taskList}" var="task">
                <div class="panel panel-${task.state}">
                    <div class="panel-heading">
                        Por ${task.createdBy.employee.fullName}, <small>actualizacion mas reciente: <g:formatDate date="${task.lastUpdated}" format="yyyy-MM-dd HH:mm"/></small>
                    </div>

                    <div class="panel-body">
                        <markdown:renderHtml>${task.description}</markdown:renderHtml>
                    </div>

                    <div class="panel-footer">
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Action <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li>
                                    <g:link resource="ticket/task" action="show" id="${task.id}" ticketId="${task.ticket.id}">Mostrar</g:link>
                                </li>
                                <g:if test="${ticket.status != 'closed'}">
                                    <li>
                                        <g:link resource="ticket/task" action="clone" id="${task.id}" ticketId="${task.ticket.id}" method="GET">Clonar</g:link>
                                    </li>
                                </g:if>
                                <g:if test="${task.ticket.status != 'closed'}">
                                    <li>
                                        <g:link resource="ticket/task" action="edit" id="${task.id}" ticketId="${task.ticket.id}">Editar</g:link>
                                    </li>
                                    <li>
                                        <a href="#" onclick="if (confirm('¿Seguro?')) { document.getElementById('deleteForm').submit(); } else { return false; }">Eliminar</a>
                                        <g:form name="deleteForm" resource="ticket/task" action="delete" params="[id: task.id, ticketId: task.ticket.id]" method="DELETE" class="hide">
                                            <g:hiddenField name="_method" value="DELETE"/>
                                        </g:form>
                                    </li>
                                </g:if>
                                <li role="separator" class="divider"></li>
                                <g:each in="${stateList}" var="state">
                                    <li>
                                        <g:link resource="ticket/task" action="changeState" params="[id: task.id, ticketId: task.ticket.id, state: state.en]" method="GET">
                                            ${state.es}
                                        </g:link>
                                    </li>
                                </g:each>
                            </ul>
                        </div>
                    </div>
                </div>
            </g:each>
        </g:if>

        <g:hasErrors bean="${task}">
            <g:renderErrors bean="${task}"/>
        </g:hasErrors>
    </content>

    <content tag="right">
        <g:if test="${ticket.status in ['pending', 'closed'] && taskList}">
            <a
                href="#"
                onclick="if (confirm('¿Seguro?')) { document.getElementById('swapForm').submit(); } else { return false; }"
                class="btn ${ ticket.status == 'pending' ? 'btn-warning' : 'btn-danger' } btn-block"
                style="margin-bottom: 15px;">
                ${ticket.status == 'pending' ? 'Cerrar' : 'Reabrir'}
            </a>

            <g:form
                name="swapForm"
                resource="ticket"
                action="swap"
                params="[id: ticket.id, status: ticket.status == 'pending' ? 'closed' : 'pending']"
                method="POST"
                class="hide">
            </g:form>
        </g:if>

        <g:if test="${ticket.status != 'closed'}">
            <g:form resource="ticket" action="assignment" params="[id: ticket.id, employeeId: ticket.employee.id]" method="POST" autocomplete="off">
                <div class="form-group">
                    <label>Recursos</label>
                    <g:select name="deviceId" from="${deviceList}" optionValue="name" optionKey="id" value="${ticket?.device?.id}" class="form-control"/>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Asignar</button>
                </div>
            </g:form>
        </g:if>
        <g:elseif test="${ticket.status == 'closed'}">
            <p>Recurso</p>
            <p>${ticket.device.name}</p>
        </g:elseif>

        <platform:attendedByEmployeeList ticket="${ticket}"/>
    </content>
</g:applyLayout>
