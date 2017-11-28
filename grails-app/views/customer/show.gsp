<g:applyLayout name="twoColumns">
    <head>
        <title>Cliente</title>
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
                    <td>${customer.name}</td>
                </tr>
                <tr>
                    <td colspan="2">Datos de representante</td>
                </tr>
                <tr>
                    <td>Nombre</td>
                    <td>${customer.representative.nameAndTitle}</td>
                </tr>
                <tr>
                    <td>Cedula</td>
                    <td>${customer.representative.identificationCard}</td>
                </tr>
                <tr>
                    <td>Correo</td>
                    <td>${customer.representative.email}</td>
                </tr>
                <tr>
                    <td>Numero de telefono</td>
                    <td>
                        <representative:renderTelephoneNumber telephoneNumber="${customer.representative.telephoneNumber}"/>
                    </td>
                </tr>
            </tbody>
        </table>

        <g:link resource="customer" action="edit" id="${customer.id}" class="btn btn-primary">Editar</g:link>

        <a class="btn btn-danger" onclick="if (confirm('Seguro?')) document.querySelector('#form').submit();">Eliminar</a>

        <g:link resource="customer" action="index" class="btn btn-default">Regresar</g:link>

        <g:form name="form" resource="customer" action="delete" id="${customer.id}" method="DELETE">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>
    </content>
</g:applyLayout>
