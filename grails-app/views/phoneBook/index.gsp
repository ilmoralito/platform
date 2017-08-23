<g:applyLayout name="oneColumn">
    <head>
        <title>Guia telefonica</title>
    </head>

    <content tag="main">
        <g:if test="${phoneBook}">
            <g:render template="/partials/phoneBook" model="[phoneBook: phoneBook]"/>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
