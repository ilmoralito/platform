<div id="refreshments" class="panel panel-default">
    <div class="panel-heading">Refrigerios [<a href="#" id="cleaner">Limpiar</a>]</div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-4">
                <div class="form-group">
                    <label for="food">Comida</label>

                    <g:each in="['Galleta', 'Sandwich', 'Reposteria']" var="food">
                        <div class="radio">
                            <label>
                                <g:radio name="refreshment.food" value="${food}"/> ${food}
                            </label>
                        </div>
                    </g:each>
                </div>
            </div>

            <div class="col-md-4">
                <div class="form-group">
                    <label for="food">Bebida</label>

                    <g:each in="['Gaseosa', 'Jugo', 'Te']" var="drink">
                        <div class="radio">
                            <label>
                                <g:radio name="refreshment.drink" value="${drink}"/> ${drink}
                            </label>
                        </div>
                    </g:each>
                </div>
            </div>

            <div class="col-md-4">
                <div class="form-group">
                    <label for="quantity">Cantidad</label>
                    <g:field name="refreshment.quantity" type="number" value="" class="form-control"/>
                </div>
            </div>
        </div>
    </div>
</div>
