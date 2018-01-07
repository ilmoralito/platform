<g:applyLayout name="threeColumns">
    <head>
        <title>Vales</title>
    </head>

    <content tag="main">
        <g:if test="${fixedVoucherList}">
            <table class="table table-hover table-bordered">
                <col width="1%">
                <col width="25%">
                <col width="10%">
                <col width="64%">

                <thead>
                    <tr>
                        <th>
                            <g:checkBox name="checkboxTrigger"/>
                        </th>
                        <th>Empleado</th>
                        <th>Precio</th>
                        <th>Servicios</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${fixedVoucherList}" var="list">
                        <tr>
                            <td colspan="4">
                                <g:link
                                    resource="fixedVoucher"
                                    action="create"
                                    params="[date: list.date]"
                                    method="GET">${list.date}</g:link>
                            </td>
                        </tr>

                        <g:each in="${list.fixedVoucherList}" var="fixedVoucher">
                            <tr class="employee${fixedVoucher.employee.id}">
                                <td class="text-center">
                                    <g:checkBox
                                        name="fixedVouchers"
                                        value="${fixedVoucher.id}"
                                        checked="false"
                                        form="printForm"
                                        class="fixedVouchers"/>
                                </td>
                                <td style="vertical-align: middle;">${fixedVoucher.employee.fullName}</td>
                                <td style="vertical-align: middle;" class="price">${fixedVoucher.price}</td>
                                <td style="vertical-align: middle;">${fixedVoucher.serviceList}</td>
                            </tr>
                        </g:each>
                    </g:each>

                    <tr>
                        <td colspan="2"></td>
                        <td colspan="2" id="totalPrice">${fixedVoucherList.fixedVoucherList.price.flatten().sum()}</td>
                    </tr>
                </tbody>
            </table>

            <g:form name="printForm" resources="fixedVoucher" action="print" method="GET">
                <button type="submit" class="btn btn-primary">
                    <span class="glyphicon glyphicon-print" aria-hidden="true"></span> Imprimir
                </button>
            </g:form>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>

    <content tag="right">
        <g:form resource="fixedVoucher" action="create" method="GET" autocomplete="off">
            <div class="form-group">
                <label for="date">Fecha</label>

                <g:textField id="datepicker" name="date" class="form-control"/>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
            </div>
        </g:form>

        <g:link resource="fixedVoucher" action="filter" method="GET" class="btn btn-default btn-block" style="margin: 0  0 15px 0;">
            <span class="glyphicon glyphicon-filter" aria-hidden="true"></span> Filtrar
        </g:link>

        <div class="form-group">
            <label>Filtrar por empleados</label>

            <g:each in="${employeeList}" var="employee" status="index">
                <div class="checkbox" style="${index == 0 ? 'margin-top: 0;' : ''}">
                    <label>
                        <g:checkBox
                            id="employee${employee.id}"
                            name="employees"
                            value="${employee.id}"
                            class="employees"/> ${employee.fullName}
                    </label>
                </div>
            </g:each>
        </div>

        <br>
        <g:hasErrors bean="${errors}">
            <g:renderErrors bean="${errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
