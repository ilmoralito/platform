<g:applyLayout name="twoColumns">
    <head>
        <title>Empleado</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td>Nombre y apellido</td>
                    <td>${employee.fullName}</td>
                </tr>

                <tr>
                    <td>Cedula</td>
                    <td>${employee.identityCard}</td>
                </tr>

                <tr>
                    <td>Tipo de contrato</td>
                    <td>
                        <platform:typeOfContract type="${employee.contract}"/>
                    </td>
                </tr>

                <tr>
                    <td>Hablitado</td>
                    <td>
                        <platform:yesNo condition="${employee.enabled}"/>
                    </td>
                </tr>

                <tr>
                    <td>Coordinaciones</td>
                    <td><g:join in="${employee.coordinations.name}"/></td>
                </tr>

                <g:if test="${employee.user}">
                    <tr>
                        <td colspan="2">DATOS DE USUARIO</td>
                    </tr>

                    <tr>
                        <td>Correo institucional</td>
                        <td>
                            ${employee.user.email}
                            <button id="copy" class="btn btn-default btn-sm">Copiar</button>
                            <small id="message"></small>
                            <input id="email" value="${employee.user.email}" style="opacity: 0;">
                        </td>
                    </tr>

                    <tr>
                        <td>Cuenta habilitada</td>
                        <td>
                            <platform:yesNo condition="${employee.user.enabled}"/>
                        </td>
                    </tr>

                    <tr>
                        <td>Roles</td>
                        <td>
                            <g:join in="${employee.user.authorities.authority}"/>
                        </td>
                    </tr>
                </g:if>
            </tbody>
        </table>

        <g:link resource="${employee}" action="edit" class="btn btn-primary" method="GET">Editar</g:link>
        <g:link resource="employee/employeeCoordination" action="index" employeeId="${employee.id}" method="GET" class="btn btn-default">Administrar coordinaciones</g:link>
        <g:if test="${employee.user}">
            <g:link resource="employee/user" action="edit" employeeId="${employee.id}" id="${employee.user.id}" method="GET" class="btn btn-default">Editar cuenta de usuario</g:link>
        </g:if>
        <g:else>
            <g:link resource="employee/user" action="create" employeeId="${employee.id}" method="GET" class="btn btn-default">Crear cuenta de usuario</g:link>
        </g:else>
    </content>
</g:applyLayout>
