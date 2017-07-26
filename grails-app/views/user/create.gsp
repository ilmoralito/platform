<g:applyLayout name="twoColumns">
    <head>
        <title>Usuario</title>
    </head>

    <content tag="main">
        <g:form resource="user" params="[employeeId: params.employeeId]" action="save" method="POST" autocomplete="off">
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Agregar" class="btn btn-primary"/>
            </div>
        </g:form>
    </content>
</g:applyLayout>
