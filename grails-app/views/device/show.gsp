<g:applyLayout name="twoColumns">
    <head>
        <title>Device</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td colspan="2" style="border: 0;">DATOS DEL RECUROS</td>
                </tr>

                <tr>
                    <td>Nombre del recurso</td>
                    <td>${device.name}</td>
                </tr>
            </tbody>
        </table>

        <g:link resource="device" action="edit" id="${device.id}" class="btn btn-primary">Editar</g:link>

        <a href="#" onclick="if (confirm('¿Seguro?')) { document.getElementById('deleteForm').submit(); } else { return false; }" class="btn btn-warning">Eliminar</a>
        <g:form resource="device" action="delete" id="${device.id}" name="deleteForm" class="hide">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>

        <g:link resource="device" class="btn btn-default">Regresar</g:link>
    </content>
</g:applyLayout>
