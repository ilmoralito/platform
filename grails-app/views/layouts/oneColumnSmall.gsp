<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="layout" content="main"/>
        <title><g:layoutTitle/></title>
        <g:layoutHead/>
    </head>
    <body>
        <div class="col-md-8 col-md-offset-2">
            <g:pageProperty name="page.main"/>

            <g:if test="${flash?.message}">
                <div class="alert alert-info">${flash.message}</div>
            </g:if>
        </div>
    </body>
</html>
