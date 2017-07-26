<g:applyLayout name="twoColumns">
    <head>
        <title>Crear datashow</title>
    </head>

    <content tag="main">
        <g:form resource="datashow" action="save" method="POST" autocomplete="off">
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Agregar" class="btn btn-primary"/>
            </div>
        </g:form>

        <g:hasErrors bean="${datashow}">
            <g:renderErrors bean="${datashow}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
