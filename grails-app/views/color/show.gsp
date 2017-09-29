<g:applyLayout name="twoColumns">
    <head>
        <title>Color</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td>Color</td>
                    <td>${color.name}</td>
                </tr>
            </tbody>
        </table>

        <g:link resource="color" action="edit" id="${color.id}" class="btn btn-default">Editar</g:link>
        <a href="#" class="btn btn-danger" onclick="if (confirm('¿Seguro?')) document.getElementById('deleteForm').submit()">Eliminar</a>
        <g:form resource="color" action="delete" id="${color.id}" name="deleteForm" class="hide">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>
    </content>
</g:applyLayout>
