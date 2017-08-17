<g:applyLayout name="twoColumns">
    <head>
        <title>Tickets</title>
    </head>

    <content tag="main">
        <div class="btn-group" role="group" style="margin-bottom: 15px;">
            <g:link resource="ticket" action="filter" params="[status: 'open']" method="GET" class="btn btn-default ${params.status == 'open' ? 'active' : ''}">
                Abiertos ${summaryStatus.open}
            </g:link>
            <g:link resource="ticket" action="filter" params="[status: 'pending']" method="GET" class="btn btn-default ${params.status == 'pending' ? 'active' : ''}">
                En proceso ${summaryStatus.pending}
            </g:link>
            <g:link resource="ticket" action="filter" params="[status: 'closed']" method="GET" class="btn btn-default ${params.status == 'closed' ? 'active' : ''}">
                Cerradas ${summaryStatus.closed}
            </g:link>
        </div>

        <g:if test="${ticketList}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 12%;">
                    <col span="1" style="width: 65%;">
                    <col span="1" style="width: 17%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 1%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Estado</th>
                        <th>Asunto</th>
                        <th>Recurso</th>
                        <th class="text-center">
                            <span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
                        </th>
                        <th class="text-center">
                            <span class="glyphicon glyphicon-bookmark" aria-hidden="true"></span>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${ticketList}" var="ticket">
                        <tr>
                            <td style="vertical-align: middle;">
                                <platform:ticketStatus status="${ticket.status}"/>
                            </td>
                            <td style="vertical-align: middle;">
                                <div>
                                    <g:link resource="ticket/task" action="index" ticketId="${ticket.id}" method="GET">
                                        ${ticket.subject}
                                    </g:link>
                                </div>
                                <div style="margin-top: 2px;">
                                    <small>#${ticket.id}</small>
                                    <small>
                                        <g:if test="${ticket.status == 'open'}">
                                            Abierto el <g:formatDate date="${ticket.dateCreated}" format="yyyy-MM-dd"/> por <g:link resource="ticket" action="filterByEmployee" params="[employeeId: ticket.employee.id]" method="GET">${ticket.employee.fullName}</g:link>
                                        </g:if>
                                        <g:elseif test="${ticket.status == 'pending'}">
                                            En progreso desde <g:formatDate date="${ticket.lastUpdated}" format="yyyy-MM-dd"/> por <g:link resource="ticket" action="filterByEmployee" params="[employeeId: ticket.employee.id]" method="GET">${ticket.employee.fullName}</g:link>
                                        </g:elseif>
                                        <g:else>
                                            Cerrado desde <g:formatDate date="${ticket.lastUpdated}" format="yyyy-MM-dd"/> por <g:link resource="ticket" action="filterByEmployee" params="[employeeId: ticket.employee.id]" method="GET">${ticket.employee.fullName}</g:link>
                                        </g:else>
                                    </small>
                                </div>
                            </td>
                            <td style="vertical-align: middle;">
                                <g:if test="${ticket.device}">
                                    <g:link resource="ticket" action="filterByDevice" params="[name: ticket.device.name]" method="GET">
                                        ${ticket?.device?.name}
                                    </g:link>
                                </g:if>
                            </td>
                            <td style="vertical-align: middle;" class="text-center">
                                <g:link resource="ticket/task" action="index" ticketId="${ticket.id}" method="GET">
                                    <span class="glyphicon glyphicon-comment" aria-hidden="true"></span> ${ticket.tasks.size()}
                                </g:link>
                            </td>
                            <td style="vertical-align: middle;" class="text-center">
                                <a href="#" class="addBookmarkTrigger">
                                    <span class="glyphicon glyphicon-bookmark" aria-hidden="true"></span>
                                </a>

                                <g:set var="springSecurityService" bean="springSecurityService"/>
                                <g:form resource="employee/bookmark" action="save" params="[employeeId: springSecurityService.currentUser.employee.id]" method="POST" class="hide">
                                    <g:hiddenField name="ticketId" value="${ticket.id}"/>
                                </g:form>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
