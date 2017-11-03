<div class="form-group">
    <label for="food">Comida</label>

    <g:each in="['Galleta', 'Sandwich', 'Reposteria']" var="food">
        <div class="radio">
            <label>
                <g:radio name="food" value="${food}" checked="${refreshment?.food == food}"/> ${food}
            </label>
        </div>
    </g:each>
</div>

<div class="form-group">
    <label for="food">Bebida</label>

    <g:each in="['Gaseosa', 'Jugo', 'Te']" var="drink">
        <div class="radio">
            <label>
                <g:radio name="drink" value="${drink}" checked="${refreshment?.drink == drink}"/> ${drink}
            </label>
        </div>
    </g:each>
</div>

<div class="form-group">
    <label for="quantity">Cantidad</label>
    <g:field name="quantity" type="number" min="1" value="${refreshment?.quantity}" class="form-control"/>
</div>
