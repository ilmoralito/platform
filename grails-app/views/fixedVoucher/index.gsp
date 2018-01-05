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
                        <th></th>
                        <th>Empleado</th>
                        <th>Precio</th>
                        <th>Servicios</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${fixedVoucherList}" var="list">
                        <tr>
                            <td colspan="4">
                                <g:link resources="fixedVoucher" action="create" params="[date: list.date]" method="GET">${list.date}</g:link>
                            </td>
                        </tr>

                        <g:each in="${list.fixedVoucherList}" var="fixedVoucher">
                            <tr>
                                <td class="text-center">
                                    <g:checkBox name="fixedVouchers" form="printForm"/>
                                </td>
                                <td style="vertical-align: middle;">${fixedVoucher.employee.fullName}</td>
                                <td style="vertical-align: middle;">${fixedVoucher.price}</td>
                                <td style="vertical-align: middle;">
                                    <g:join in="${fixedVoucher.serviceList}"/>
                                </td>
                            </tr>
                        </g:each>
                    </g:each>

                    <tr>
                        <td colspan="2"></td>
                        <td colspan="2">${fixedVoucherList.fixedVoucherList.price.flatten().sum()}</td>
                    </tr>
                </tbody>
            </table>

            <g:form>
                <button type="submit" class="btn btn-primary">Imprimir</button>
            </g:form>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>

    <content tag="right">
        <g:form resources="fixedVoucher" action="create" method="GET" autocomplete="off">
            <div class="form-group">
                <label for="date">Fecha</label>

                <g:textField id="datepicker" name="date" class="form-control"/>
            </div>
            
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block">Confirmar</button>
            </div>
        </g:form>

        <g:link class="btn btn-primary btn-block">Vales de la semana actual</g:link>

        <g:link class="btn btn-primary btn-block">Filtar</g:link>

        <g:hasErrors bean="${errors}">
            <g:renderErrors bean="${errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
