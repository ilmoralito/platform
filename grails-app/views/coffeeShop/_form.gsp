<div class="form-group">
    <label for="name">Nombre</label>
    <g:textField name="name" value="${coffeeShop?.name}" class="form-control"/>
</div>

<div class="form-group">
    <label for="breakfast">Precio del desayuno</label>
    <g:field type="number" name="breakfast" value="${coffeeShop?.breakfast}" min="1" class="form-control"/>
</div>

<div class="form-group">
    <label for="lunch">Precio del almuerzo</label>
    <g:field type="number" name="lunch" value="${coffeeShop?.lunch}" min="1" class="form-control"/>
</div>

<div class="form-group">
    <label for="dinner">Precio de la cena</label>
    <g:field type="number" name="dinner" value="${coffeeShop?.dinner}" min="1" class="form-control"/>
</div>

<div class="form-group">
    <label for="others">Precio de otros</label>
    <g:field type="number" name="others" value="${coffeeShop?.others}" min="1" class="form-control"/>
</div>
