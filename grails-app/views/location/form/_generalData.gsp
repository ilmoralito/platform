<div class="panel panel-default">
    <div class="panel-heading">Datos generales</div>
    <div class="panel-body">
        <div class="form-group">
            <label for="place">Lugar</label>
            <select name="place" id="place" class="form-control">
                <g:each in="${classroomList}" var="classroom">
                    <optgroup label="${classroom.code}">
                        <g:each in="${classroom.classrooms}" var="data">
                            <option
                                value="${data.id}"
                                ${data.code == location?.place?.code ? 'selected' : ''}
                                data-classroom-wifi="${data.wifi}"
                                data-classroom-capacity="${data.capacity}"
                                data-classroom-airConditioned="${data.airConditioned}"
                                data-classroom-powerOutletNumber="${data.powerOutletNumber}">
                                ${data.code} ${data?.name ?: ''}
                            </option>
                        </g:each>
                    </optgroup>>
                </g:each>
            </select>
            <a href="#" style="font-size: .8em;" id="place-details-trigger">Detalle</a>
            <div id="place-details-container"></div>
        </div>

        <div class="form-group">
            <label for="startDateAndTime">Fecha y hora de inicio</label>
            <g:field type="date" name="startDateAndTime" value="${location?.startDateAndTime?.format('yyyy-MM-dd')}" readonly class="form-control"/>
        </div>

        <div class="form-group">
            <label for="interval">Duracion</label>
            <select name="interval" id="interval" multiple="true" size="10" class="form-control">
                <g:each in="${hourList}" var="hour">
                    <option value="${hour.twentyFourHourFormat}" ${hour.twentyFourHourFormat == startTime || hour.twentyFourHourFormat == endTime ? 'selected' : ''}>
                        ${hour.twelveHourFormat}
                    </option>
                </g:each>
            </select>
        </div>

        <div class="form-group">
            <label for="participants">Numero de participantes</label>
            <g:field name="participants" type="number" min="1" step="1" value="${location?.participants}" class="form-control"/>
            <span id="helpBlock" class="help-block"></span>
        </div>
    </div>
</div>
