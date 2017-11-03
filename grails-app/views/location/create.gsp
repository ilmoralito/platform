<g:applyLayout name="twoColumns">
    <head>
        <title>Crear ubicacion</title>
    </head>

    <content tag="main">
        <g:form
            resource="employee/activity/location"
            params="[employeeId: params.employeeId, activityId: params.activityId]"
            method="POST">

            <g:render template="form/generalData"/>
            <g:render template="form/montage"/>
            <g:render template="form/requirements"/>
            <g:render template="form/observation"/>

            <div class="form-group">
                <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>

                <g:render template="returnButton"/>
            </div>
        </g:form>

        <g:hasErrors bean="${errors}">
            <g:renderErrors bean="${errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
