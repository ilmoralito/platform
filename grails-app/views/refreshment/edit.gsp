<g:applyLayout name="twoColumns">
    <head>
        <title>Refrigerio</title>
    </head>

    <content tag="main">
        <g:form
            resource="employee/activity/location/refreshment"
            action="update"
            params="[employeeId: params.employeeId, activityId: params.activityId, locationId: params.locationId, id: params.id]"
            method="PUT"
            autocomplete="off">

            <g:hiddenField name="_method" value="PUT"/>
            <g:render template="form"/>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Actualizar</button>

                <a class="btn btn-danger" onclick="if (confirm('¿Seguro?')) document.querySelector('#form').submit()">Eliminar</a>

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

        <g:form
            name="form"
            resource="employee/activity/location/refreshment"
            action="delete"
            params="[employeeId: params.employeeId, activityId: params.activityId, locationId: params.locationId, id: params.id]"
            method="DELETE">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>

        <g:hasErrors bean="${refreshment}">
            <g:renderErrors bean="${refreshment}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
