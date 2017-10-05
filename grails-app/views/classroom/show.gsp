<g:applyLayout name="twoColumns">
    <head>
        <title>Aula</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td>Codigo</td>
                    <td>${classroom.code}</td>
                </tr>

                <tr>
                    <td>Nombre</td>
                    <td>${classroom.name}</td>
                </tr>

                <tr>
                    <td>Capacidad</td>
                    <td>${classroom?.capacity}</td>
                </tr>

                <tr>
                    <td>WIFI</td>
                    <td>
                        <platform:yesNo condition="${classroom?.wifi}"/>
                    </td>
                </tr>

                <tr>
                    <td>Climatizado</td>
                    <td>
                        <platform:yesNo condition="${classroom?.airConditioned}"/>
                    </td>
                </tr>

                <tr>
                    <td>Numero de enchufes</td>
                    <td>${classroom.powerOutletNumber}</td>
                </tr>
            </tbody>
        </table>

        <g:link resource="classroom" action="edit" id="${classroom.id}" class="btn btn-primary">Editar</g:link>

        <a href="#" class="btn btn-warning" onclick="if (confirm('¿Seguro?')) document.getElementById('deleteForm').submit()">Eliminar</a>
        <g:form resource="classroom" action="delete" id="${classroom.id}" name="deleteForm">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>
    </content>
</g:applyLayout>
