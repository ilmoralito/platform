<g:applyLayout name="twoColumns">
    <head>
        <title>Crear carrera</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td>Nombre de la carrera</td>
                    <td>${career.name}</td>
                </tr>
            </tbody>
        </table>

        <g:link resource="career" action="edit" id="${career.id}" class="btn btn-primary">Editar</g:link>

        <a href="#" class="btn btn-warning" onclick="if (confirm('¿Seguro?')) { document.getElementById('deleteForm').submit(); } else { return false; }">Eliminar</a>

        <g:form resource="career" action="delete" id="${career.id}" name="deleteForm" class="hide">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>
    </content>
</g:applyLayout>
