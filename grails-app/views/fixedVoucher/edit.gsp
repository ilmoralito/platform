<g:applyLayout name="twoColumns">
    <head>
        <title>Editar vale fijo</title>
    </head>

    <content tag="main">
        <g:form resources="fixedVoucher" action="update" id="${fixedVoucher.id}" method="PUT" autocomplete="off">
            <div class="form-group">
                <label for="date">Fecha</label>

                <g:textField name="date" value="${fixedVoucher.date.format('yyyy-MM-dd')}" class="form-control" readonly="true"/>
            </div>

            <div class="form-group">
                <label for="employee">Empleados</label>

                <select name="employee" id="employeeList" class="form-control">
                    <g:each in="${employeeList}" var="employee">
                        <option
                            value="${employee.id}"
                            data-coordination-list="${groovy.json.JsonOutput.toJson(employee.getCoordinations().collect { [id: it.id, name: it.name] })}"
                            ${fixedVoucher.employee == employee ? 'selected' : ''}>
                            ${employee.fullName}
                        </option>
                    </g:each>
                </select>
            </div>

            <div class="form-group">
                <label for="coordination">Coordinacion</label>

                <g:select
                    name="coordination"
                    from="${fixedVoucher.employee.getCoordinations()}"
                    optionKey="id"
                    optionValue="name"
                    value="${fixedVoucher.coordination}"
                    class="form-control"/>
            </div>

            <g:if test="${coffeeShopList.size() == 1}">
                <g:hiddenField name="coffeeShop" value="${coffeeShopList[0].id}"/>
            </g:if>
            <g:else>
                <div class="form-group">
                    <label for="coffeeShop">Cafetin</label>
                    <select id="coffeeShop" name="coffeeShop" class="form-control">
                        <g:each in="${coffeeShopList}" var="coffeeShop">
                            <option
                                value="${coffeeShop.id}"
                                ${coffeeShop == fixedVoucher.coffeeShop ? 'selected' : ''}
                                data-breakfast-price="${coffeeShop.breakfast}"
                                data-lunch-price="${coffeeShop.lunch}"
                                data-dinner-price="${coffeeShop.dinner}"
                                data-others-price="${coffeeShop.others}">${coffeeShop.name}</option>
                        </g:each>
                    </select>
                </div>
            </g:else>

            <div class="form-group">
                <label for="price">Precio</label>

                <g:textField name="price" value="${fixedVoucher.price}" readonly="true" class="form-control"/>
            </div>

            <div class="form-group">
                <label>Servicios</label>

                <div class="checkbox" style="margin-top: 0;">
                    <label>
                        <g:checkBox
                            name="breakfast"
                            data-price="${fixedVoucher.coffeeShop.breakfast}"
                            checked="${fixedVoucher?.breakfast}"
                            class="prices"/> Desayuno
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox
                            name="lunch"
                            data-price="${fixedVoucher.coffeeShop.lunch}"
                            checked="${fixedVoucher?.lunch}"
                            class="prices"/> Almuerzo
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox
                            name="dinner"
                            data-price="${fixedVoucher.coffeeShop.dinner}"
                            checked="${fixedVoucher?.dinner}"
                            class="prices"/> Cena
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox
                            name="others"
                            data-price="${fixedVoucher.coffeeShop.others}"
                            checked="${fixedVoucher?.others}"
                            class="prices"/> Otros
                    </label>
                </div>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Actualizar</button>

                <g:link
                    resources="fixedVoucher"
                    action="show"
                    id="${fixedVoucher.id}"
                    method="GET"
                    class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${errors}">
            <g:renderErrors bean="${errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
