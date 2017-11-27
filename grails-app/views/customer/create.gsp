<g:applyLayout name="twoColumns">
    <head>
        <title>Crear cliente</title>
    </head>

    <content tag="main">
        <g:form resource="customer" action="save" method="POST" autocomplete="off">
            <g:render template="form"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
        </g:form>

        <br>
        <g:hasErrors bean="${errors}">
            <g:renderErrors bean="${errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>