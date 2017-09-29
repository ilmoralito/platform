<g:applyLayout name="twoColumns">
    <head>
        <title>Editar color</title>
    </head>

    <content tag="main">
        <g:form resource="color" action="update" id="${color.id}" method="PUT" autocomplete="off">
            <g:hiddenField name="_method" value="PUT"/>
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
                <g:link resource="${color}" method="GET" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${color}">
            <g:renderErrors bean="${color}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
