<ul class="nav nav-tabs">
    <li role="presentation" class="${actionName == 'index' ? 'active' : ''}">
        <g:link resource="employee/ticket" employeeId="${params.employeeId}" action="index" method="GET">Abiertas</g:link>
    </li>
    <li role="presentation" class="${actionName in ['summary', 'summaryInYear', 'summaryInYearAndMonth'] ? 'active' : ''}">
        <g:link resource="employee/ticket" action="summary" employeeId="${params.employeeId}">Sumario</g:link>
    </li>
</ul>
