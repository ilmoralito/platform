<div class="form-group">
    <label for="coordinationId">Coordinacion</label>
    <g:select name="coordinationId" from="${coordinationList}" optionKey="id" optionValue="name" value="${employeeCoordination?.coordination?.id}" class="form-control"/>
</div>

<div class="form-group">
    <label for="position">Autoridad</label>
    <g:select name="position" from="['Coordinador', 'Colaborador']" keys="['manager', 'assistant']" value="${employeeCoordination?.position}" class="form-control"/>
</div>

<div class="form-group">
    <label for="jobTitle">Nombre del cargo</label>
    <g:textField name="jobTitle" value="${employeeCoordination?.jobTitle}" class="form-control"/>
</div>
