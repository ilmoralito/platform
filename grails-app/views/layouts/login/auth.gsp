<!doctype html>
<html lang="en" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>
            <g:layoutTitle default="Plataforma"/>
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>

        <asset:stylesheet src="auth.css"/>
        <g:layoutHead/>
    </head>
    <body>
        <g:render template="/partials/navbar"/>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="text-center" style="margin-top: 25px;">
                        <asset:image src="logo.png"/>
                    </div>

                    <g:layoutBody/>

                    <g:if test="${flash?.message}">
                        <div class="alert alert-info">
                            ${flash.message}
                        </div>
                    </g:if>
                </div>
            </div>
        </div>
    </body>
</html>
