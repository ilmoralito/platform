<g:applyLayout name="twoColumns">
    <head>
        <title>Editar aula</title>
    </head>

    <content tag="main">
        <g:form resource="classroom" action="update" id="${classroom.id}" autocomplete="off">
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
                <g:link resource="classroom" action="show" id="${classroom.id}" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${classroom}">
            <g:renderErrors bean="${classroom}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
