<g:applyLayout name="twoColumns">
    <head>
        <title>Editar cliente</title>
    </head>

    <content tag="main">
        <g:form resource="customer" action="update" id="${customer.id}" method="PUT" autocomplete="off">
            <g:hiddenField name="_method" value="PUT"/>
            <g:render template="form"/>

            <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
        </g:form>

        <br>
        <g:hasErrors bean="${errors}">
            <g:renderErrors bean="${errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>