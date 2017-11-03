<g:applyLayout name="twoColumns">
    <head>
        <title>Refrigerio</title>
    </head>

    <content tag="main">
        <g:form
            resource="employee/activity/location/refreshment"
            action="save"
            params="[employeeId: params.employeeId, activityId: params.activityId, locationId: params.locationId]"
            method="POST"
            autocomplete="off">
            <g:render template="form"/>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Agregar</button>
                <g:link
                    resource="employee/activity/location"
                    action="show"
                    params="[employeeId: params.employeeId, activityId: params.activityId, id: params.locationId]"
                    method="GET"
                    class="btn btn-default">
                        Regresar
                </g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${refreshment}">
            <g:renderErrors bean="${refreshment}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
