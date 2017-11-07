<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="layout" content="errors"/>
        <title>Acceso prohibido</title>
    </head>
    <body>
        <h2 class="text-center">ERROR 403</h2>

        <p>Intentas acceder a un recurso valido pero que ha sido marcado como prohibido.</p>

        <p>Porfavor contacta al adminsitrador de la aplicacion.</p>

        <g:link uri="${request.getHeader('referer')}" class="btn btn-primary">Regresar</g:link>
    </body>
</html>