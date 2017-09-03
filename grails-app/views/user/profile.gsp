<g:applyLayout name="twoColumns">
    <head>
        <title>Perfil</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <table class="table table-hover border-less">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td style="vertical-align: middle;">Nombre y apellido</td>
                    <td>
                        <span id="target">${user.employee.fullName}</span>
                        <span id="actionButtons">
                            <button id="edit" class="btn btn-default btn-sm" data-id="${user.employee.id}">Editar</button>
                        </span>
                    </td>
                </tr>

                <tr>
                    <td>Cedula de identidad</td>
                    <td colspan="4">${user.employee.identityCard}</td>
                </tr>

                <tr>
                    <td>Tipo de contrato</td>
                    <td colspan="4">
                        <platform:typeOfContract type="${user.employee.contract}"/>
                    </td>
                </tr>

                <tr>
                    <td>Hablitado</td>
                    <td colspan="4">
                        <platform:yesNo condition="${user.employee.enabled}"/>
                    </td>
                </tr>

                <tr>
                    <td>Coordinaciones</td>
                    <td colspan="4">
                        <g:join in="${user.employee.coordinations.name}"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="4">DATOS DE USUARIO</td>
                </tr>

                <tr>
                    <td>Correo institucional</td>
                    <td colspan="4">${user.email}</td>
                </tr>

                <tr>
                    <td>Cuenta habilitada</td>
                    <td colspan="4">
                        <platform:yesNo condition="${user.enabled}"/>
                    </td>
                </tr>

                <tr>
                    <td>Roles</td>
                    <td colspan="4">
                        <g:join in="${user.authorities.authority}"/>
                    </td>
                </tr>
            </tbody>
        </table>
    </content>
</g:applyLayout>
