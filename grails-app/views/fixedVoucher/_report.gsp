<table class="table table-hover table-bordered">
    <col width="25%">
    <col width="10%">
    <col width="65%">

    <thead>
        <tr>
            <th>Empleado</th>
            <th>Precio</th>
            <th>Servicios</th>
        </tr>
    </thead>

    <tbody>
        <g:each in="${fixedVoucherList}" var="list">
            <tr>
                <td colspan="3">
                    <b>${list.date}</b>
                </td>
            </tr>

            <g:each in="${list.fixedVoucherList}" var="fixedVoucher">
                <tr>
                    <td>${fixedVoucher.employee.fullName}</td>
                    <td>${fixedVoucher.price}</td>
                    <td>${fixedVoucher.serviceList}</td>
                </tr>
            </g:each>
        </g:each>
    </tbody>
</table>
