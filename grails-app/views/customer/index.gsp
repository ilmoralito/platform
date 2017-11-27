<g:applyLayout name="twoColumns">
    <head>
        <title>Lista de clientes</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <div class="pull-right">
                <g:link resource="customer" action="create" method="GET" class="btn btn-primary">
                    Crear actividad
                </g:link>
            </div>
        </div>

        <g:if test="${customerList}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Lista de clientes</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${customerList}" var="customer">
                        <tr>
                            <td>
                                <g:link resource="customer" action="show" id="${customer.id}" method="GET">${customer.name}</g:link>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
