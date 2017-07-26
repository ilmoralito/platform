<div class="form-group">
    <label for="fullName">Nombre y apellido</label>
    <g:textField name="fullName" value="${employee?.fullName}" class="form-control"/>
</div>

<div class="form-group">
    <label for="identityCard">Cedula</label>
    <g:textField name="identityCard" value="${employee?.identityCard}" class="form-control"/>
</div>

<div class="form-group">
    <label for="contract">Tipo de contrato</label>
    <g:select name="contract" from="['Fijo', 'Horario']" keys="['permanent', 'schedule']" value="${employee?.contract}" class="form-control"/>
</div>

<g:if test="${actionName == 'edit' || actionName == 'update'}">
    <div class="form-group">
        <div class="checkbox">
            <label>
                <g:checkBox name="enabled" value="${employee?.enabled}"/>
                Habilitado
            </label>
        </div>
    </div>
</g:if>
