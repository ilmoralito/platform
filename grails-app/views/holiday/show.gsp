<g:applyLayout name="twoColumns">
    <head>
        <title>Dia feriado</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td>Fecha</td>
                    <td>
                        <g:formatDate date="${holiday.date}" format="yyyy-MM-dd"/>
                    </td>
                </tr>

                <tr>
                    <td>Nombre</td>
                    <td>${holiday.name}</td>
                </tr>

                <tr>
                    <td>Wiki</td>
                    <td>${holiday.wiki}</td>
                </tr>
            </tbody>
        </table>

        <g:link resources="holiday" action="edit" id="${holiday.id}" method="GET" class="btn btn-primary">Editar</g:link>
        <a href="#" onclick="if (confirm('¿Seguro?')) { document.getElementById('deleteForm').submit(); } else { return false; }" class="btn btn-danger">Eliminar</a>
        <g:form resource="holiday" action="delete" id="${holiday.id}" name="deleteForm" class="hide">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>
    </content>
</g:applyLayout>
