<div class="form-group">
    <label for="trademark">Marca</label>
    <g:select name="trademark" from="['EPSON', 'BENQ']" value="${datashow?.trademark}" class="form-control"/>
</div>

<div class="form-group">
    <label for="model">Modelo</label>
    <g:textField name="model" value="${datashow?.model}" class="form-control"/>
</div>

<div class="form-group">
    <label for="serialNumber">Numero de serie</label>
    <g:textField name="serialNumber" value="${datashow?.serialNumber}" class="form-control"/>
</div>

<div class="form-group">
    <label for="code">Codigo</label>
    <g:textField name="code" value="${datashow?.code}" class="form-control"/>
</div>

<div class="form-group">
    <div class="checkbox">
        <label>
            <g:checkBox name="hdmi" value="${datashow?.hdmi}"/>
            HDMI
        </label>
    </div>
</div>

<div class="form-group">
    <div class="checkbox">
        <label>
            <g:checkBox name="wifi" value="${datashow?.wifi}"/>
            WIFI
        </label>
    </div>
</div>

<div class="form-group">
    <div class="checkbox">
        <label>
            <g:checkBox name="ethernet" value="${datashow?.ethernet}"/>
            Ethernet
        </label>
    </div>
</div>

<g:if test="${actionName == 'edit'}">
    <div class="form-group">
        <div class="checkbox">
            <label>
                <g:checkBox name="enabled" value="${datashow?.enabled}"/>
                Habilitado
            </label>
        </div>
    </div>
</g:if>
