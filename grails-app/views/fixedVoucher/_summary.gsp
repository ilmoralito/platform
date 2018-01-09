<table class="table table-hover table-bordered">
    <caption>${caption}</caption>

    <col width="40%">
    <col width="10%">
    <col width="10%">
    <col width="10%">
    <col width="10%">
    <col width="10%">
    <col width="10%">

    <thead>
        <tr>
            <th>${label}</th>
            <th>Cantidad</th>
            <th>Precios</th>
            <th>Desayunos</th>
            <th>Almuerzos</th>
            <th>Cenas</th>
            <th>Otros</th>
        </tr>
    </thead>

    <tbody>
        <g:each in="${summaryList}" var="summary">
            <tr>
                <td>${summary.label}</td>
                <td>${summary.quantity}</td>
                <td>${summary.price}</td>
                <td>${summary.breakfast}</td>
                <td>${summary.lunch}</td>
                <td>${summary.dinner}</td>
                <td>${summary.others}</td>
            </tr>
        </g:each>

        <tr>
            <td>TOTAL</td>
            <td>${summaryList.quantity.sum()}</td>
            <td>${summaryList.price.sum()}</td>
            <td>${summaryList.breakfast.sum()}</td>
            <td>${summaryList.lunch.sum()}</td>
            <td>${summaryList.dinner.sum()}</td>
            <td>${summaryList.others.sum()}</td>
        </tr>
    </tbody>
</table>
