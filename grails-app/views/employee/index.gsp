<g:applyLayout name="twoColumns">
    <head>
        <title>Empleados</title>
    </head>

    <content tag="main">
        <g:link resource="employee" action="create" class="btn btn-primary pull-right">Crear empleado</g:link>

        <g:if test="${employeeList}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Lista de empleados</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${employeeList}" var="employee">
                        <tr>
                            <td>
                                <g:link resource="${employee}" action="show">${employee.fullName}</g:link>
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
