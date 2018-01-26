<g:applyLayout name="threeColumns">
    <head>
        <title>Crear vale fijo</title>
    </head>

    <content tag="main">
        <g:if test="${fixedVoucherList}">
            <table class="table table-hover">
                <col width="5%">
                <col width="25%">
                <col width="10%">
                <col width="60%">

                <thead>
                    <tr>
                        <th></th>
                        <th>Empleado</th>
                        <th>Precio</th>
                        <th>Servicios</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${fixedVoucherList}" var="fixedVoucher">
                        <tr>
                            <td>
                                <g:link resources="fixedVoucher" action="show" id="${fixedVoucher.id}" method="GET">
                                    Mostrar
                                </g:link>
                            </td>
                            <td>${fixedVoucher.employee.fullName}</td>
                            <td>${fixedVoucher.price}</td>
                            <td>${fixedVoucher.serviceList}</td>
                        </tr>
                    </g:each>

                    <tr>
                        <td colspan="2"></td>
                        <td colspan="2">${fixedVoucherList.price.sum()}</td>
                    </tr>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Si datos que mostrar</p>
        </g:else>
    </content>

    <content tag="right">
        <g:form resources="fixedVoucher" action="save" method="POST" autocomplete="off">
            <div class="form-group">
                <label for="date">Fecha</label>

                <g:textField name="date" value="${date.format('yyyy-MM-dd')}" class="form-control" readonly="true"/>
            </div>

            <div class="form-group">
                <label for="employee">Empleados</label>

                <select name="employee" id="employeeList" class="form-control">
                    <g:each in="${employeeList}" var="employee">
                        <option
                            value="${employee.id}"
                            data-coordination-list="${groovy.json.JsonOutput.toJson(employee.getCoordinations().collect { [id: it.id, name: it.name] })}">
                            ${employee.fullName}
                        </option>
                    </g:each>
                </select>
            </div>

            <div class="form-group">
                <label for="coordination">Coordinacion</label>

                <g:select
                    name="coordination"
                    from="${employeeList.first().getCoordinations()}"
                    optionKey="id"
                    optionValue="name"
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

                <g:textField name="price" value="0" readonly="true" class="form-control"/>
            </div>

            <div class="form-group">
                <label>Servicios</label>

                <div class="checkbox" style="margin-top: 0;">
                    <label>
                        <g:checkBox
                            name="breakfast"
                            data-price="${coffeeShopList[0].breakfast}"
                            class="prices"/> Desayuno
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox
                            name="lunch"
                            data-price="${coffeeShopList[0].lunch}"
                            class="prices"/> Almuerzo
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox
                            name="dinner"
                            data-price="${coffeeShopList[0].dinner}"
                            class="prices"/> Cena
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox
                            name="others"
                            data-price="${coffeeShopList[0].others}"
                            class="prices"/> Otros
                    </label>
                </div>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
            </div>

            <g:hasErrors bean="${errors}">
                <g:renderErrors bean="${errors}"/>
            </g:hasErrors>
        </g:form>
    </content>
</g:applyLayout>
