<g:applyLayout name="twoColumns">
    <head>
        <title>Editar coordinacion</title>
    </head>

    <content tag="main">
        <g:form resource="coordination" action="update" id="${coordination.id}" autocomplete="off">
            <g:hiddenField name="_method" value="PUT"/>
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
            </div>
        </g:form>

        <g:hasErrors bean="${coordination}">
            <g:renderErrors bean="${coordination}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
