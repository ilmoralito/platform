<g:applyLayout name="twoColumns">
    <head>
        <title>Carrera</title>
    </head>

    <content tag="main">
        <g:form resource="career" action="save" autocomplete="off">
            <div class="form-group">
                <label for="name">Nombre</label>
                <g:textField name="name" class="form-control"/>
            </div>
            
            <div class="form-group">
                <g:submitButton name="send" value="Agregar" class="btn btn-primary"/>
            </div>
        </g:form>

        <g:hasErrors bean="${career}">
            <g:renderErrors bean="${career}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
