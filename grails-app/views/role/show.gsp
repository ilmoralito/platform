<g:applyLayout name="twoColumns">
    <head>
        <title>Role</title>
    </head>

    <content tag="main">
        <table class="table table-hover" style="margin-top: 15px;">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td>Autoridad</td>
                    <td>${role.authority}</td>
                </tr>
            </tbody>
        </table>

        <g:link resource="role" action="edit" id="${role.id}" class="btn btn-primary">Editar</g:link>

        <a href="#" onclick="if (confirm('¿Seguro?')) { document.getElementById('deleteForm').submit(); } else { return false; }" class="btn btn-warning">Eliminar</a>
        <g:form name="deleteForm" resource="role" action="delete" id="${role.id}" method="DELETE" class="hide">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>
        <g:link resource="role" action="index" method="GET" class="btn btn-default">Regresar</g:link>
    </content>
</g:applyLayout>
