<g:applyLayout name="threeColumns">
    <head>
        <title>Lista de cafetines</title>
    </head>

    <content tag="main">
        <g:if test="${coffeeShopList}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Cafetines</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${coffeeShopList}" var="coffeeshop">
                        <tr>
                            <td>
                                <g:link resource="${coffeeshop}" method="GET">${coffeeshop.name}</g:link>
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

    <content tag="right">
        <g:form resource="coffeeShop" action="save" method="POST" autocomplete="off">
            <g:render template="form"/>

            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
            </div>
        </g:form>

        <g:hasErrors bean="${errors}">
            <g:renderErrors bean="${errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
