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

                <tr>
                    <td>Lugar</td>
                    <td>${location.place.code} ${location.place.name}</td>
                </tr>

                <tr>
                    <td>Fecha y hora de inicio</td>
                    <td>
                        <g:formatDate date="${location.startDateAndTime}" format="yyyy-MM-dd hh:mm"/>
                    </td>
                </tr>

                <tr>
                    <td>Hora de clausura</td>
                    <td>
                        <g:formatDate date="${location.endDateAndTime}" format="hh:mm"/>
                    </td>
                </tr>

                <tr>
                    <td>Participantes</td>
                    <td>${location.participants}</td>
                </tr>

                <tr>
                    <td colspan="2">
                        <b>MONTAJE</b>
                    </td>
                </tr>

                <tr>
                    <td>Tipo de montaje</td>
                    <td>${location.typeOfAssembly}</td>
                </tr>

                <tr>
                    <td>Colores de manteleria</td>
                    <td>
                        <g:join in="${colorList.name}"/>
                    </td>
                </tr>

                <tr>
                    <td>Podio</td>
                    <td>
                        <platform:yesNo condition="${location.podium}"/>
                    </td>
                </tr>

                <tr>
                    <td>Mesa para expositor</td>
                    <td>
                        <platform:yesNo condition="${location.displayTable}"/>
                    </td>
                </tr>

                <tr>
                    <td>Banderas</td>
                    <td>
                        <platform:yesNo condition="${location.flags}"/>
                    </td>
                </tr>

                <tr>
                    <td>Mesa para proyector</td>
                    <td>
                        <platform:yesNo condition="${location.projectorTable}"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <b>REQUERIMIENTOS</b>
                    </td>
                </tr>

                <tr>
                    <td>Datashow</td>
                    <td>
                        <platform:yesNo condition="${location.datashow}"/>
                    </td>
                </tr>

                <tr>
                    <td>Himno nacional</td>
                    <td>
                        <platform:yesNo condition="${location.nationalAnthem}"/>
                    </td>
                </tr>

                <tr>
                    <td>Himno de la universidad</td>
                    <td>
                        <platform:yesNo condition="${location.universityAnthem}"/>
                    </td>
                </tr>

                <tr>
                    <td>Marcha triunfal</td>
                    <td>
                        <platform:yesNo condition="${location.triumphalAnthem}"/>
                    </td>
                </tr>

                <tr>
                    <td>Sonido</td>
                    <td>
                        <platform:yesNo condition="${location.sound}"/>
                    </td>
                </tr>

                <tr>
                    <td>Agua</td>
                    <td>
                        <platform:yesNo condition="${location.water}"/>
                    </td>
                </tr>

                <tr>
                    <td>Cafe</td>
                    <td>
                        <platform:yesNo condition="${location.coffee}"/>
                    </td>
                </tr>

                <tr>
                    <td>Numero de botellas de agua</td>
                    <td>${location.waterBottles}</td>
                </tr>

                <g:if test="${location.refreshment}">
                    <tr>
                        <td colspan="2">
                            <b>REFRIGERIOS</b>
                        </td>
                    </tr>

                    <tr>
                        <td>Comida</td>
                        <td>${location.refreshment.food}</td>
                    </tr>

                    <tr>
                        <td>Bebida</td>
                        <td>${location.refreshment.drink ?: 'Sin bebidas'}</td>
                    </tr>

                    <tr>
                        <td>Cantidad</td>
                        <td>${location.refreshment.quantity}</td>
                    </tr>
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

                <tr>
                    <td colspan="2">
                        <b>METADATOS</b>
                    </td>
                </tr>

                <tr>
                    <td>Organizador</td>
                    <td>${location.activity.organizedBy.name}</td>
                </tr>

                <tr>
                    <td>Creado por</td>
                    <td>${location.activity.employee.fullName}</td>
                </tr>

                <tr>
                    <td>Estado</td>
                    <td>
                        <activity:state currentState="${location.activity.state}"/>
                    </td>
                </tr>

                <tr>
                    <td>Fecha de creacion</td>
                    <td>
                        <g:formatDate date="${location.dateCreated}" format="yyyy-MM-dd hh:mm"/>
                    </td>
                </tr>

                <tr>
                    <td>Actualizacion mas reciente</td>
                    <td>
                        <g:formatDate date="${location.lastUpdated}" format="yyyy-MM-dd hh:mm"/>
                    </td>
                </tr>
            </tbody>
        </table>

        <g:link
            resource="employee/activity/location"
            action="show"
            params="[employeeId: params.employeeId, activityId: params.activityId, id: location.id]"
            method="GET"
            class="btn btn-default">
            Regresar
        </g:link>
    </content>
</g:applyLayout>
