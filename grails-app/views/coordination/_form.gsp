<div class="form-group">
    <label for="name">Nombre</label>
    <g:textField name="name" value="${coordination?.name}" class="form-control"/>
</div>

<div class="form-group">
    <label for="acronym">Siglas</label>
    <g:textField name="acronym" value="${coordination?.acronym}" class="form-control"/>
</div>

<div class="form-group">
    <label for="extensionNumber">Extension</label>
    <g:textField name="extensionNumber" value="${coordination?.extensionNumber}" class="form-control"/>
</div>

<div class="form-group">
    <label for="copyFee">Cuota de copias</label>
    <g:textField name="copyFee" value="${coordination?.copyFee}" class="form-control"/>
</div>

<div class="form-group">
    <label for="area">Area</label>
    <g:select
        name="area"
        from="['Academica', 'Administrativo', 'Facultad']"
        keys="['academic', 'administrative', 'school']"
        value="${coordination?.area}"
        class="form-control"/>
</div>

<g:if test="${colorList}">
    <div class="form-group">
        <label for="colors">Colores</label>

        <g:each in="${colorList}" var="color">
            <div class="form-group">
                <div class="checkbox">
                    <label>
                        <g:checkBox name="colors" value="${color.id}" checked="${color in coordinationColorList}"/> ${color.name}
                    </label>
                </div>
            </div>
        </g:each>
    </div>
</g:if>
