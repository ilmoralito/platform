<g:applyLayout name="twoColumns">
    <head>
        <title>Vale</title>
    </head>

    <content tag="main">
        <g:set var="isEmployeeVoucher" value="${voucher.instanceOf(ni.edu.ucc.leon.EmployeeVoucher)}"/>

        <table class="table table-hover">
            <col width='25%'>
            <col width='75%'>

            <tbody>
                <tr>
                    <td>A nombre de</td>
                    <td>${isEmployeeVoucher ? voucher.employee.fullName : voucher.guest.fullName}</td>
                </tr>

                <tr>
                    <td>Fecha</td>
                    <td>
                        <g:formatDate date="${voucher.date}" format="yyyy-MM-dd"/>
                    </td>
                </tr>

                <tr>
                    <td>Cafetin</td>
                    <td>${voucher.coffeeShop.name}</td>
                </tr>

                <tr>
                    <td>Organizador/Area</td>
                    <td>${voucher.coordination.name} / <coordination:area area="${voucher.coordination.area}"/></td>
                </tr>

                <tr>
                    <td>Nombre de actividad</td>
                    <td>
                        <g:link
                            resource="employee/activity"
                            action="show"
                            params="[employeeId: params.employeeId, id: params.activityId]"
                            method="GET">${voucher.activity.name}</g:link>
                    </td>
                </tr>

                <tr>
                    <td>Valor</td>
                    <td>${voucher.price}</td>
                </tr>

                <tr>
                    <td>Servicios</td>
                    <td>
                        <voucher:getServices voucher="${voucher}"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">METAS</td>
                </tr>

                <tr>
                    <td>Fecha de creacion</td>
                    <td>
                        <g:formatDate date="${voucher.dateCreated}" format="yyyy-MM-dd"/>
                    </td>
                </tr>

                <tr>
                    <td>Fecha de mas reciente actualizacion</td>
                    <td>
                        <g:formatDate date="${voucher.lastUpdated}" format="yyyy-MM-dd"/>
                    </td>
                </tr>
            </tbody>
        </table>

        <a onclick="if (confirm('¿Seguro?')) document.querySelector('#form').submit()" class="btn btn-danger">Eliminar</a>

        <g:link
            resource="employee/activity/voucher"
            action="index"
            params="[employeeId: params.employeeId, activityId: params.activityId, type: isEmployeeVoucher ? 'employee' : 'guest']"
            method="GET"
            class="btn btn-default">Regresar</g:link>

        <g:form
            name="form"
            resource="employee/activity/voucher"
            action="delete"
            params="[employeeId: params.employeeId, activityId: params.activityId, id: voucher.id]"
            method="DELETE">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>
    </content>
</g:applyLayout>
