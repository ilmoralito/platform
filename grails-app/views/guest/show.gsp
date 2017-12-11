<g:applyLayout name="twoColumns">
    <head>
        <title>Visita</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <col width="25%">
            <col width="75%">

            <tbody>
                <tr>
                    <td>Nombre y apellido</td>
                    <td>${guest.fullName}</td>
                </tr>
            </tbody>
        </table>

        <g:link resource="guest" action="edit" id="${guest.id}" class="btn btn-primary">Editar</g:link>

        <a class="btn btn-danger" onclick="if (confirm('¿Seguro?')) document.querySelector('#form').submit();">Eliminar</a>

        <g:form resource="guest" action="delete" id="${guest.id}" name="form">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>
    </content>
</g:applyLayout>
