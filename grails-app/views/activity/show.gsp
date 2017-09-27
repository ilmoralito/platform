<g:applyLayout name="twoColumns">
    <head>
        <title>Actividad</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td>Nombre</td>
                    <td>${activity.name}</td>
                </tr>
                <tr>
                    <td>Estado</td>
                    <td>
                        <activity:state state="${activity.state}"/>
                    </td>
                </tr>
                <tr>
                    <td>Creado por</td>
                    <td>
                        ${activity.employee.fullName}
                    </td>
                </tr>
                <tr>
                    <td>Organizado por</td>
                    <td>${activity.organizedBy.name}</td>
                </tr>
                <tr>
                    <td>Fecha de creacion</td>
                    <td>
                        <g:formatDate date="${activity.dateCreated}" format="yyyy-MM-dd HH:mm"/>
                    </td>
                </tr>
            </tbody>
        </table>

        <g:link resource="employee/activity" action="edit" id="${activity.id}" employeeId="${params.employeeId}" method="GET" class="btn btn-default">
            Editar
        </g:link>
        <g:link class="btn btn-default">Administrar ubicaciones</g:link>
        <a href="#" class="btn btn-danger" onclick="if (confirm('Estas seguro?')) document.querySelector('#deleteForm').submit()">
            Eliminar
        </a>
        <g:form name="deleteForm" resource="employee/activity" params="[id: activity.id, employeeId: params.employeeId]" action="delete" method="DELETE" class="hide">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>
    </content>
</g:applyLayout>
