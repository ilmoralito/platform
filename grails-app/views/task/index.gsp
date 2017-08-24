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
                        <div class="clearfix">
                            <div class="pull-left">
                                <g:link resource="ticket/task" action="show" ticketId="${task.ticket.id}" id="${task.id}" class="btn btn-default btn btn-primary btn-sm">
                                    Mostrar
                                </g:link>
                            </div>

                            <g:if test="${ticket.status != 'closed'}">
                                <g:form resource="ticket/task" action="clone" params="[id: task.id, ticketId: task.ticket.id]" method="POST" class="pull-left" style="margin-left: 5px;">
                                    <button type="submit" value class="btn btn-default btn-sm">Clonar</button>
                                </g:form>
                            </g:if>

                            <g:form resource="ticket/task" action="updateState" params="[id: task.id, ticketId: task.ticket.id]" method="PUT" class="form-inline pull-right">
                                <g:hiddenField name="_method" value="PUT"/>
                                <div class="form-group">
                                    <g:select
                                        name="state"
                                        from="['Predeterminado', 'Informacion', 'Advertencia']"
                                        keys="['default', 'info', 'warning']"
                                        value="${task.state}"
                                        class="form-control input-sm"/>
                                </div>

                                <button type="submit" id="updateState" value class="btn btn-primary btn-sm">Aplicar</button>
                            </g:form>
                        </div>
                    </div>
                </div>
            </g:each>
        </g:if>
    </content>

    <content tag="right">
        <g:if test="${ticket.status in ['pending', 'closed'] && ticket.tasks}">
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
            <g:form resource="ticket" action="assignment" id="${params.ticketId}" method="POST" autocomplete="off">
                <div class="form-group">
                    <label>Recursos</label>
                    <g:select name="device" from="${deviceList}" optionValue="name" optionKey="id" value="${ticket?.device?.id}" class="form-control"/>
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
