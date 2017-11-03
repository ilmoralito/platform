<g:applyLayout name="twoColumns">
    <head>
        <title>Editar ubicacion</title>
    </head>

    <content tag="main">
        <g:form
            resource="employee/activity/location"
            action="update"
            params="[employeeId: params.employeeId, activityId: params.activityId, id: params.id]"
            method="PUT">

            <g:hiddenField name="_method" value="PUT"/>

            <g:render template="form/generalData"/>
            <g:render template="form/montage"/>
            <g:render template="form/requirements"/>
            <g:render template="form/observation"/>

            <div class="form-group">
                <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>

                <g:link
                    resource="employee/activity/location"
                    action="show"
                    params="[employeeId: params.employeeId, activityId: params.activityId, id: params.id]"
                    method="GET"
                    class="btn btn-default">
                    Regresar
                </g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${errors}">
            <g:renderErrors bean="${errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
