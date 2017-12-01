<div class="form-group">
    <label for="name">Nombre de la actividad</label>
    <g:textField name="name" value="${actionName in ['create', 'save'] ? '' : activity?.name}" list="nameList" class="form-control"/>
    <datalist id="nameList">
        <g:each in="${nameList}" var="name">
            <option value="${name.name}">
        </g:each>
    </datalist>
</div>

<sec:ifAllGranted roles='ROLE_PROTOCOL'>
    <div class="form-group">
        <label for="customer">Selecciona cliente</label>
        <g:select
            name="customer"
            from="${customerList}"
            optionKey="id"
            optionValue="name"
            value="${actionName in ['edit', 'update'] ? activity?.customer?.id : ''}"
            class="form-control"/>
    </div>
</sec:ifAllGranted>

<g:if test="${employeeCoordinations.size() == 1}">
    <g:hiddenField name="organizedBy" value="${employeeCoordinations[0].coordination.id}"/>
</g:if>
<g:else>
    <div class="form-group">
        <label for="organizedBy">Organizado por</label>
        <select name="organizedBy" id="organizedBy" class="form-control">
            <g:each in="${employeeCoordinations}" var="employeeCoordination">
                <option value="${employeeCoordination.coordination.id}">
                    ${employeeCoordination.coordination.name}
                </option>
            </g:each>
        </select>
    </div>
</g:else>
