<g:applyLayout name="twoColumns">
    <head>
        <title>Coordinacion</title>
    </head>

    <content tag="main">
        <g:form resource="coordination" action="save" autocomplete="off">
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Agregar" class="btn btn-primary"/>
            </div>
        </g:form>

        <g:hasErrors bean="${coordination}">
            <g:renderErrors bean="${coordination}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
