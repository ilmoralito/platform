<g:applyLayout name="threeColumns">
    <head>
        <title>Resumen de reporte de vales fijos</title>
    </head>

    <content tag="main">
        <g:if test="${fixedVoucherList}">
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

            <g:render
                template="summary"
                model="[caption: 'Sumario por area', label: 'Area', summaryList: areaSummary]"/>

            <g:render
                template="summary"
                model="[caption: 'Sumario por coordinacion', label: 'Coordinacion', summaryList: coordinationSummary]"/>

            <g:render
                template="summary"
                model="[caption: 'Sumario por empleado', label: 'Empleado', summaryList: employeeSummary]"/>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>

    <content tag="right">
        <g:link class="btn btn-primary btn-block">Compartir</g:link>
    </content>
</g:applyLayout>
