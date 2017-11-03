<g:applyLayout name="twoColumns">
    <head>
        <title>Ubicacion</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td colspan="2" style="border-top: 0;">
                        <b>DATOS GENERALES</b>
                    </td>
                </tr>

                <tr>
                    <td>Nombre de la actividad</td>
                    <td>${location.activity.name}</td>
                </tr>
                <g:each in="${generalData}" var="data">
                    <tr>
                        <td>
                            <location:property property="${data.key}"/>
                        </td>
                        <td>
                            <g:if test="${data.value instanceof ni.edu.ucc.leon.Classroom}">
                                ${data.value.code} ${data.value.name}
                            </g:if>
                            <g:elseif test="${data.key == 'startDateAndTime'}">
                                <g:formatDate date="${data.value}" format="yyyy-MM-dd hh:mm"/>
                            </g:elseif>
                            <g:elseif test="${data.key == 'endDateAndTime'}">
                                <g:formatDate date="${data.value}" format="hh:mm"/>
                            </g:elseif>
                            <g:else>
                                ${data.value}
                            </g:else>
                        </td>
                    </tr>
                </g:each>

                <tr>
                    <td colspan="2" style="border-top: 0;">
                        <b>MONTAJE</b>
                    </td>
                </tr>

                <g:if test="${colorList}">
                    <tr>
                        <td>Colores de manteleria</td>
                        <td>
                            <g:join in="${colorList.name}"/>
                        </td>
                    </tr>
                </g:if>
                <g:each in="${mounting}" var="data">
                    <tr>
                        <td>
                            <location:property property="${data.key}"/>
                        </td>
                        <td>
                            <g:if test="${data.value instanceof Boolean}">
                                <platform:yesNo condition="${data.value}"/>
                            </g:if>
                            <g:else>
                                ${data.value}
                            </g:else>
                        </td>
                    </tr>
                </g:each>

                <tr>
                    <td colspan="2" style="border-top: 0;">
                        <b>REQUERIMIENTOS</b>
                    </td>
                </tr>

                <g:each in="${requirements}" var="data">
                    <tr>
                        <td>
                            <location:property property="${data.key}"/>
                        </td>
                        <td>
                            <g:if test="${data.value instanceof Boolean}">
                                <platform:yesNo condition="${data.value}"/>
                            </g:if>
                            <g:else>
                                ${data.value}
                            </g:else>
                        </td>
                    </tr>
                </g:each>

                <g:if test="${refreshment}">
                    <tr>
                        <td colspan="2" style="border-top: 0;">
                            <b>REFRIGERIOS</b>
                        </td>
                    </tr>

                    <g:each in="${refreshment}" var="data">
                        <tr>
                            <td>
                                <location:property property="${data.key}"/>
                            </td>
                            <td>${data.value}</td>
                        </tr>
                    </g:each>
                </g:if>

                <g:if test="${location.observation}">
                    <tr>
                        <td colspan="2">
                            <b>OBSERVACION</b>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2">${location.observation}</td>
                    </tr>
                </g:if>
            </tbody>
        </table>

        <g:form
            name="deleteLocationForm"
            resource="employee/activity/location"
            params="[employeeId: params.employeeId, activityId: params.activityId, id: location.id]"
            method="DELETE">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>

        <g:link
            resource="employee/activity/location"
            action="edit"
            params="[employeeId: params.employeeId, activityId: params.activityId, id: params.id]"
            method="GET"
            class="btn btn-primary">
            Editar
        </g:link>

        <a class="btn btn-danger" onclick="if (confirm('¿Seguro?')) document.querySelector('#deleteLocationForm').submit()">Eliminar</a>

        <g:if test="${!location?.refreshment}">
            <g:link
                resource="employee/activity/location/refreshment"
                action="create"
                params="[employeeId: params.employeeId, activityId: params.activityId, locationId: location.id]"
                method="GET"
                class="btn btn-default">
                Agregar refrigerio
            </g:link>
        </g:if>
        <g:else>
            <g:link
                resource="employee/activity/location/refreshment"
                action="edit"
                params="[employeeId: params.employeeId, activityId: params.activityId, locationId: location.id, id: location.refreshment.id]"
                method="GET"
                class="btn btn-default">
                Editar refrigerio
            </g:link>
        </g:else>

        <g:link
            resource="employee/activity/location"
            action="showAll"
            params="[employeeId: params.employeeId, activityId: params.activityId, locationId: location.id]"
            class="btn btn-default"
            method="GET">
            Mostar detalles
        </g:link>

        <g:render template="returnButton"/>
    </content>
</g:applyLayout>
