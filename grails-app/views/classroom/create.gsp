<g:applyLayout name="twoColumns">
    <head>
        <title>Crear aula</title>
    </head>

    <content tag="main">
        <g:form resource="classroom" action="save" autocomplete="off">
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Agregar" class="btn btn-primary"/>
            </div>
        </g:form>

        <g:hasErrors bean="${classroom}">
            <g:renderErrors bean="${classroom}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
