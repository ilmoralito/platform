<div class="panel panel-default">
    <div class="panel-heading">Requerimientos</div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-4">
                <label>Medios</label>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="datashow" checked="${location?.datashow}"/> Datashow
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="nationalAnthem" checked="${location?.nationalAnthem}"/> Himno nacional
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="universityAnthem" checked="${location?.universityAnthem}"/> Himno de la universidad
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="triumphalAnthem" checked="${location?.triumphalAnthem}"/> Marcha triunfal
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="sound" checked="${location?.sound}"/> Sonido
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="microphone" checked="${location?.microphone}"/> Microfono
                    </label>
                </div>
            </div>

            <div class="col-md-4">
                <label>Otros</label>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="water" checked="${location?.water}"/> Agua
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="coffee" checked="${location?.coffee}"/> Cafe
                    </label>
                </div>
            </div>

            <div class="col-md-4">
                <div class="form-group">
                    <label for="waterBottles">Numero de botellas de agua</label>
                    <g:field name="waterBottles" type="number" min="0" value="${location?.waterBottles}" class="form-control"/>
                </div>
            </div>
        </div>
    </div>
</div>
