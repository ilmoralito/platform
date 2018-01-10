<g:applyLayout name="threeColumns">
    <head>
        <title>Lista de vales</title>
    </head>

    <content tag="main">
        <g:if test="${model.employeeVoucherList || model.guestVoucherList}">
            <table class="table table-hover table-bordered">
                <col width="25%">
                <col width="5%">
                <col width="69%">
                <col width="1%">

                <thead>
                    <tr>
                        <th>A nombre de</th>
                        <th>Valor</th>
                        <th>Servicios</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <g:if test="${model.employeeVoucherList}">
                        <g:render template="summary" model="[label: 'EMPLEADOS', voucherList: model.employeeVoucherList]"/>
                    </g:if>

                    <g:if test="${model.guestVoucherList}">
                        <g:render template="summary" model="[label: 'VISITAS', voucherList: model.guestVoucherList]"/>
                    </g:if>

                    <tr>
                        <td>TOTAL</td>
                        <td colspan="3">${(model?.employeeVoucherList?.vouchers?.price?.flatten()?.sum() ?: 0) + (model?.guestVoucherList?.vouchers?.price?.flatten()?.sum() ?: 0)}</td>
                    </tr>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>

    <content tag="right">
        <sec:ifAllGranted roles='ROLE_PROTOCOL'>
            <g:if test="${activity.state == 'authorized'}">
                <g:link controller="voucher" action="print" params="[activityId: params.activityId]" method="GET" class="btn btn-primary btn-block">
                    Imprimir vales
                </g:link>
            </g:if>
        </sec:ifAllGranted>
        <br>

        <g:render template="nav"/>

        <g:form
            resource="employee/activity/voucher"
            action="${params.type == 'employee' ? 'save' : 'store'}"
            params="[employeeId: params.employeeId, activityId: params.activityId, type: params.type]"
            method="POST"
            autocomplete="off">

            <div class="form-group">
                <label for="date">Fecha</label>
                <g:if test="${model.locationDateList.size() == 1}">
                    <g:textField
                        name="date"
                        value="${model.locationDateList[0].date}"
                        class="form-control"
                        readonly="true"/>
                </g:if>
                <g:else>
                    <g:select
                        name="date"
                        from="${model.locationDateList}"
                        optionKey="date"
                        optionValue="date"
                        data-activity-id="${params.activityId}"
                        data-activity-type="${params.type}"
                        class="form-control"/>
                </g:else>
            </div>

            <g:if test="${model.coffeeShopList.size() == 1}">
                <g:hiddenField name="coffeeShop" value="${model.coffeeShopList[0].id}"/>
            </g:if>
            <g:else>
                <div class="form-group">
                    <label for="coffeeShop">Cafetin</label>
                    <select id="coffeeShop" name="coffeeShop" class="form-control">
                        <g:each in="${model.coffeeShopList}" var="coffeeShop">
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
                <g:field name="price" type="text" value="0" readonly="true" class="form-control"/>
            </div>

            <div class="form-group">
                <g:if test="${params.type == 'employee'}">
                    <div id="employeeList">
                        <g:each in="${model.employeeList}" var="employee">
                            <div class="checkbox">
                                <label>
                                    <g:checkBox name="employees" value="${employee.id}" checked="false"/>
                                    ${employee.fullName}
                                </label>
                            </div>
                        </g:each>
                    </div>
                </g:if>
                <g:else>
                    <div id="guestList">
                        <g:each in="${model.guestList}" var="guest">
                            <div class="checkbox">
                                <label>
                                    <g:checkBox name="guests" value="${guest.id}" checked="false"/>
                                    ${guest.fullName}
                                </label>
                            </div>
                        </g:each>
                    </div>
                </g:else>
            </div>

            <div class="form-group">
                <div class="checkbox">
                    <label>
                        <g:checkBox name="breakfast" data-price="${model.coffeeShopList[0].breakfast}" class="prices"/> Desayuno
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="lunch" data-price="${model.coffeeShopList[0].lunch}" class="prices"/> Almuerzo
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="dinner" data-price="${model.coffeeShopList[0].dinner}" class="prices"/> Cena
                    </label>
                </div>

                <div class="checkbox">
                    <label>
                        <g:checkBox name="others" data-price="${model.coffeeShopList[0].others}" class="prices"/> Otros
                    </label>
                </div>
            </div>

            <div class="form-group">
                <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
            </div>
        </g:form>

        <g:hasErrors bean="${errors}">
            <g:renderErrors bean="${errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
