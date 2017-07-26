<g:applyLayout name="twoColumns">
    <head>
        <title>Editar empleado</title>
    </head>

    <content tag="main">
        <g:form resource="employee" action="update" id="${employee.id}" method="PUT" autocomplete="off">
            <g:hiddenField name="_method" value="PUT"/>
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
                <g:link resource="${employee}" action="show" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${employee}">
            <g:renderErrors bean="${employee}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
