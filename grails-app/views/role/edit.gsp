<g:applyLayout name="twoColumns">
    <head>
        <title>Editar role</title>
    </head>

    <content tag="main">
        <g:form resource="role" action="update" id="${role.id}" method="PUT" autocomplete="off">
            <g:hiddenField name="_method" value="PUT"/>

            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="sendTask" value="Actualizar" class="btn btn-primary"/>
                <g:link resource="role" action="show" id="${role.id}" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${task}">
            <g:renderErrors bean="${task}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
