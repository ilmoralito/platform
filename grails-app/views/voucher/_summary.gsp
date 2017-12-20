<tr>
    <td colspan="4">
        <b>${label}</b>
    </td>
</tr>

<g:each in="${voucherList}" var="summary">
    <tr>
        <td colspan="4">
            <b>
                <g:formatDate date="${summary.date}" format="yyyy-MM-dd"/>
            </b>
        </td>
    </tr>

    <g:each in="${summary.vouchers}" var="voucher">
        <tr>
            <td>
                <g:link
                    resource="employee/activity/voucher"
                    action="show"
                    params="[employeeId: params.employeeId, activityId: params.activityId, id: voucher.id]"
                    method="GET">${voucher.toName}</g:link>
            </td>
            <td>${voucher.price}</td>
            <td>
                <g:join in="${voucher.services}"/>
            </td>
            <td>
                <a onclick="if (confirm('¿Seguro?')) document.querySelector(`#deleteForm${voucher.id}`).submit()">
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                </a>

                <g:form
                    name="deleteForm${voucher.id}"
                    resource="employee/activity/voucher"
                    action="delete"
                    params="[employeeId: params.employeeId, activityId: params.activityId, id: voucher.id]"
                    method="DELETE">
                    <g:hiddenField name="_method" value="DELETE"/>
                </g:form>
            </td>
        </tr>
    </g:each>
</g:each>
