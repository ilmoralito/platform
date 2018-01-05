<g:applyLayout name="twoColumns">
    <head>
        <title>Cafetin</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <col width="25%">
            <col width="75%">

            <tbody>
                <tr>
                    <td>Nombre</td>
                    <td>${coffeeShop.name}</td>
                </tr>

                <tr>
                    <td>Precio del desayuno</td>
                    <td>${coffeeShop.breakfast}</td>
                </tr>

                <tr>
                    <td>Precio del almuerzo</td>
                    <td>${coffeeShop.lunch}</td>
                </tr>

                <tr>
                    <td>Precio de la cena</td>
                    <td>${coffeeShop.dinner}</td>
                </tr>

                <tr>
                    <td>Precio de otros</td>
                    <td>${coffeeShop.others}</td>
                </tr>
            </tbody>
        </table>

        <g:link resource="coffeeShop" action="edit" id="${coffeeShop.id}" class="btn btn-primary">Editar</g:link>

        <a class="btn btn-danger" onclick="if (confirm('¿Seguro?')) document.querySelector('#form').submit();">Eliminar</a>

        <g:link resource="coffeeShop" action="index" method="GET" class="btn btn-default">Regresar</g:link>

        <g:form resource="coffeeShop" action="delete" id="${coffeeShop.id}" name="form">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>
    </content>
</g:applyLayout>
