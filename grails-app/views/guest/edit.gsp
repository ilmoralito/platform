<g:applyLayout name="twoColumns">
    <head>
        <title>Editar visita</title>
    </head>

    <content tag="main">
        <g:form resource="guest" action="update" id="${guest.id}" method="PUT" autocomplete="off">
            <g:hiddenField name="_method" value="PUT"/>

            <div class="form-group">
                <label for="fullName">Nombre y apellido</label>
                <g:textField name="fullName" value="${guest?.fullName}" class="form-control"/>
            </div>

            <div class="form-group">
                <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>

                <g:link resource="${guest}" method="GET" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${guest}">
            <g:renderErrors bean="${guest}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
