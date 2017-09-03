<g:applyLayout name="twoColumns">
    <head>
        <title>Clave de paso</title>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:form action="changePassword" method="POST" autocomplete="off">
            <div class="form-group">
                <label for="currentPassword">Clave de paso actual</label>
                <g:passwordField name="currentPassword" class="form-control"/>
            </div>

            <div class="form-group">
                <label for="newPassword">Nueva clave de paso</label>
                <g:passwordField name="newPassword" class="form-control"/>
            </div>

            <div class="form-group">
                <label for="repeatNewPassword">Repetir nueva clave de paso</label>
                <g:passwordField name="repeatNewPassword" class="form-control"/>
            </div>

            <div class="form-group">
                <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
            </div>
        </g:form>
    </content>
</g:applyLayout>
