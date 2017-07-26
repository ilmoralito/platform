<g:applyLayout name="twoColumns">
    <head>
        <title>Editar coordinacion</title>
    </head>

    <content tag="main">
        <g:form resource="career" action="update" id="${career.id}" autocomplete="off">
            <g:hiddenField name="_method" value="PUT"/>
            
            <div class="form-group">
                <label for="name">Nombre</label>
                <g:textField name="name" value="${career?.name}" class="form-control"/>
            </div>

            <div class="form-group">
                <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
                <g:link resource="career" action="show" id="${career.id}" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${career}">
            <g:renderErrors bean="${career}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
