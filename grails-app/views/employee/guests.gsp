<g:applyLayout name="${coordinationList.size() == 1 ? 'twoColumns' : 'threeColumns'}">
    <head>
        <title>Visitas</title>
    </head>

    <content tag="main">
        <g:if test="${guestList}">
            <table class="table table-hover">
                <col width="1%">
                <col width="99%">

                <thead>
                    <tr>
                        <th></th>
                        <th>Visitas</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${guestList}" var="guest">
                        <tr>
                            <td class="text-center">
                                <g:checkBox
                                    form="form"
                                    name="guestList"
                                    value="${guest.id}"
                                    checked="${guest in coordinationGuestList}"/>
                            </td>
                            <td>${guest.fullName}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>

            <g:form name="form" resource="employee" action="store" params="[employeeId: params.employeeId]" method="POST">
                <g:set var="coordination" value="${params.coordination ?: coordinationList[0].name}"/>

                <g:hiddenField name="coordination" value="${coordination}"/>

                <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
            </g:form>

            <br>
            <g:hasErrors bean="${errors}">
                <g:renderErrors bean="${errors}"/>
            </g:hasErrors>
        </g:if>
        <g:else>
            <p>No hay visitas registradas aun!</p>
        </g:else>
    </content>

    <content tag="right">
        <g:set var="currentCoordination" value="${params.coordination ?: coordinationList[0].name}"/>

        <ul class="nav nav-pills nav-stacked">
            <g:each in="${coordinationList}" var="coordination">
                <li class="${currentCoordination == coordination.name ? 'active' : ''}" role="presentation">
                    <g:link
                        resource="employee"
                        action="guests"
                        method="GET"
                        params="[employeeId: params.employeeId, coordination: coordination.name]">${coordination.name}</g:link>
                </li>
            </g:each>
        </ul>
    </content>
</g:applyLayout>
