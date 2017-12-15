<g:applyLayout name="twoColumns">
    <head>
        <title>Cafetin</title>
    </head>

    <content tag="main">
        <g:form resource="coffeeShop" action="update" id="${coffeeShop.id}" autocomplete="off">
            <g:render template="form"/>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Actualizar</button>

                <g:link resource="${coffeeShop}" method="GET" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${errors}">
            <g:renderErrors bean="${errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
