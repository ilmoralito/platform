<g:applyLayout name="threeColumns">
    <head>
        <title>Reporte de vales fijos</title>
    </head>

    <content tag="main">
        <table class="table table-hover table-bordered">
            <col width="15%">
            <col width="95%">

            <thead>
                <tr>
                    <th class="text-center">Mes</th>
                    <th>Cantidad</th>
                </tr>
            </thead>

            <tbody>
                <g:each in="${results}" var="result">
                    <g:set var="p" value="${params.year ? [year: params.year, month: result.pivot] : [month: result.pivot]}"/>

                    <tr>
                        <td class="text-center">
                            <g:link
                                resource="fixedVoucher"
                                action="reportSummary"
                                params="${p}"
                                method="GET">${result.month}</g:link>
                        </td>
                        <td>${result.total}</td>
                    </tr>
                </g:each>

                <tr>
                    <td class="text-center">TOTAL</td>
                    <td>${results.total.sum()}</td>
                </tr>
            </tbody>
        </table>
    </content>

    <content tag="right">
        <ul class="nav nav-pills nav-stacked">
            <li role="presentation" class="${!params.year ? 'active' : ''}">
                <g:link resource="fixedVoucher" action="report" method="GET">Global</g:link>
            </li>

            <g:each in="${yearList}" var="year">
                <li role="presentation" class="${params.int('year') == year ? 'active' : ''}">
                    <g:link resource="fixedVoucher" action="report" params="[year: year]" method="GET">${year}</g:link>
                </li>
            </g:each>
        </ul>
    </content>
</g:applyLayout>
