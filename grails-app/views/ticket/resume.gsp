<g:applyLayout name="twoColumns">
    <head>
        <title>Ticket</title>
    </head>

    <content tag="main">
        <g:render template="ticket" model="[ticket: ticket]"/>

        <g:link uri="${request.getHeader('referer')}" method="GET" class="btn btn-default">Regresar</g:link>
    </content>
</g:applyLayout>
