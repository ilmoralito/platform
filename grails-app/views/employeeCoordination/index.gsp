<g:applyLayout name="threeColumns">
    <head>
        <title>Coordinaciones de empleado</title>
    </head>

    <content tag="main">
        <div style="margin-bottom: 10px;">
            <g:link resource="employee" action="show" id="${params.employeeId}" method="GET" class="btn btn-default">Regresar</g:link>
        </div>

        <g:if test="${employeeCoordinationList}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 40%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 5%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Coordinacion</th>
                        <th>Autoridad</th>
                        <th>Cargo</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${employeeCoordinationList}" var="employeeCoordination">
                        <tr>
                            <td>${employeeCoordination.coordination.name}</td>
                            <td>
                                <platform:position position="${employeeCoordination.position}"/>
                            </td>
                            <td>${employeeCoordination.jobTitle}</td>
                            <td>
                                <g:link
                                    resource="employee/employeeCoordination"
                                    action="edit"
                                    params="[employeeId: params.employeeId, id: employeeCoordination.id]"
                                    method="GET">
                                    Editar
                                </g:link>
                            </td>
                            <td>
                                <a href="#" onclick="if (confirm('¿Seguro?')) { document.getElementById('deleteForm').submit(); } else { return false; }">Eliminar</a>
                                <g:form name="deleteForm" resource="employee/employeeCoordination" action="delete" params="[employeeId: params.employeeId, id: employeeCoordination.id]" method="DELETE" class="hide">
                                    <g:hiddenField name="_method" value="DELETE"/>
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

        <g:hasErrors bean="${employeeCoordination}">
            <g:renderErrors bean="${employeeCoordination}"/>
        </g:hasErrors>
    </content>

    <content tag="right">
        <g:if test="${coordinationList}">
            <g:form resource="employee/employeeCoordination" action="save" params="[employeeId: params.employeeId]" method="POST" autocomplete="off">
                <g:render template="form"/>

                <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
            </g:form>
        </g:if>
        <g:else>
            <p class="alert alert-info">Sin coordinaciones</p>
        </g:else>
    </content>
</g:applyLayout>
