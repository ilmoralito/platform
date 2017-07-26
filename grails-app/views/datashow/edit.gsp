<g:applyLayout name="twoColumns">
    <head>
        <title>Editar datashow</title>
    </head>

    <content tag="main">
        <g:form resource="datashow" action="update" id="${datashow.id}" method="PUT" autocomplete="off">
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
                <g:link resource="${datashow}" action="show" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${datashow}">
            <g:renderErrors bean="${datashow}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
