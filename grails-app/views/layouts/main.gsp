<!doctype html>
<html lang="en" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>
            <g:layoutTitle default="Plataforma"/>
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>

        <asset:stylesheet src="app.css"/>
        <g:layoutHead/>
    </head>
    <body>
        <g:render template="/partials/navbar"/>

        <div class="container-fluid">
            <div class="row">
                <g:layoutBody/>
            </div>
        </div>

        <g:if test="${controllerName == 'user' && actionName == 'profile'}">
            <asset:javascript src="updateProfile.js"/>
        </g:if>
        <g:else>
            <asset:javascript src="application.js"/>
        </g:else>
    </body>
</html>
