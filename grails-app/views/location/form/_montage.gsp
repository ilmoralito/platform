<div class="panel panel-default">
    <div class="panel-heading">Montaje</div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-4">
                <label for="Tipo de montaje">Tipo de montaje</label>
                <g:each in="${montageList}" var="montage">
                    <div class="radio">
                        <label>
                            <g:radio name="typeOfAssembly" value="${montage}" checked="${montage == location?.typeOfAssembly}"/> ${montage}
                        </label>
                    </div>
                </g:each>
            </div>

            <div class="col-md-4">
                <label for="colors">Colores de manteleria</label> <br>
                <g:each in="${colorList}" var="color">
                    <div class="checkbox">
                        <label>
                            <g:checkBox name="colors" value="${color.id}" checked="${color in organizerColorList}"/> ${color.name}
                        </label>
                    </div>
                </g:each>
            </div>

            <div class="col-md-4">
                <label>Montaje</label>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="podium" checked="${location?.podium}"/> Podio
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="displayTable" checked="${location?.displayTable}"/> Mesa para expositor
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="flags" checked="${location?.flags}"/> Banderas
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="projectorTable" checked="${location?.projectorTable}"/> Mesa para projector
                    </label>
                </div>
            </div>
        </div>
    </div>
</div>
