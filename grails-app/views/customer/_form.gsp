<div class="form-group">
    <label for="name">Nombre</label>
    <g:textField name="name" value="${customer?.name}" class="form-control"/>
</div>

<h4>Datos de representante</h4>

<div class="form-group">
    <label for="fullName">Nombre</label>
    <g:textField name="fullName" value="${customer?.representative?.fullName}" class="form-control"/>
</div>

<div class="form-group">
    <label for="identificationCard">Cedula</label>
    <g:textField name="identificationCard" value="${customer?.representative?.identificationCard}" class="form-control"/>
</div>

<div class="form-group">
    <label for="email">Correo</label>
    <g:textField name="email" value="${customer?.representative?.email}" class="form-control"/>
</div>

<div class="form-group">
    <label for="telephoneNumber">Telefono</label>
    <g:textField name="telephoneNumber" value="${customer?.representative?.telephoneNumber}" class="form-control"/>
</div>
