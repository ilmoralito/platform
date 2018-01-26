<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
    </head>
    <body>
        <g:if test="${phoneBook}">
            <g:render template="/partials/phoneBook" model="[phoneBook: phoneBook]"/>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </body>
</html>
