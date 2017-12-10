<ul class="nav nav-pills nav-stacked">
    <li role="presentation" class="${!params.year ? 'active' : ''}">
        <g:link resource="activity" action="reportSummary" method="GET">Global</g:link>
    </li>

    <g:each in="${yearList}" var="year">
        <li role="presentation" class="${params.int('year') == year ? 'active' : ''}">
            <g:link resource="activity" action="reportSummary" params="[year: year]" method="GET">${year}</g:link>
        </li>
    </g:each>
</ul>
