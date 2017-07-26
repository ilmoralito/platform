<div class="form-group">
    <label for="code">Codigo</label>
    <g:textField name="code" value="${classroom?.code}" class="form-control"/>
</div>

<div class="form-group">
    <label for="name">Nombre</label>
    <g:textField name="name" value="${classroom?.name}" class="form-control"/>
</div>

<div class="form-group">
    <label for="capacity">Capacidad</label>
    <g:textField name="capacity" value="${classroom?.capacity}" class="form-control"/>
</div>

<div class="form-group">
    <div class="checkbox">
        <label>
            <g:checkBox name="wifi" value="true" checked="${classroom?.wifi}"/>
            WIFI
        </label>
    </div>
</div>

<div class="form-group">
    <div class="checkbox">
        <label>
            <g:checkBox name="airConditioned" value="true" checked="${classroom?.airConditioned}"/>
            Climatizado
        </label>
    </div>
</div>
