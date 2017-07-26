<g:applyLayout name="twoColumns">
    <head>
        <title>Tarea</title>
    </head>

    <content tag="main">
        <g:form controller="task" action="update" params="[id: task.id, ticketId: task.ticket.id]" method="PUT" autocomplete="off">
            <g:hiddenField name="_method" value="PUT"/>

            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="sendTask" value="Actualizar" class="btn btn-primary"/>
                <g:link resource="ticket/task" action="show" ticketId="${params.ticketId}" id="${params.id}" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${task}">
            <g:renderErrors bean="${task}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
