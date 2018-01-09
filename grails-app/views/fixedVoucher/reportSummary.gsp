<g:applyLayout name="threeColumns">
    <head>
        <title>Resumen de reporte de vales fijos</title>
    </head>

    <content tag="main">
        <g:if test="${fixedVoucherList}">
            <g:render template="report" model="[fixedVoucherList: fixedVoucherList]"/>

            <g:render
                template="summary"
                model="[caption: 'Sumario por area', label: 'Area', summaryList: model.areaSummary]"/>

            <g:render
                template="summary"
                model="[caption: 'Sumario por coordinacion', label: 'Coordinacion', summaryList: model.coordinationSummary]"/>

            <g:render
                template="summary"
                model="[caption: 'Sumario por empleado', label: 'Empleado', summaryList: model.employeeSummary]"/>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>

    <content tag="right">
        <p class="alert alert-info">Este reporte se enviara al director administrativo y delegado de la sede</p>

        <g:link
            resource="fixedVoucher"
            action="sendReport"
            params="${params.year ? [year: params.year, month: params.month] : [month: params.month]}"
            method="GET"
            class="btn btn-primary btn-block">
            <span class="glyphicon glyphicon-send" aria-hidden="true"></span> Compartir
        </g:link>
    </content>
</g:applyLayout>
