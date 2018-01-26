<!doctype html>
<html lang="en" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>Tablero</title>
        <asset:stylesheet src="app.css"/>
        <g:layoutHead/>
    </head>
    <body>
        <g:render template="/partials/navbar"/>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                    <g:render template="/partials/sidebar"/>
                </div>

                <g:layoutBody />
            </div>
        </div>

        <asset:javascript src="dashboard.js"/>
    </body>
</html>
