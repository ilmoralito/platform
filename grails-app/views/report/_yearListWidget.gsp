<ul class="nav nav-pills nav-stacked">
    <li role="presentation" class="${!params.year ? 'active' : ''}">
        <g:link action="${yearListWidget.globalActionName}">Global</g:link>
    </li>

    <g:each in="${yearListWidget.yearList}" var="year">
        <li role="presentation" class="${params.int('year') == year.year ? 'active' : ''}">
            <g:link action="${yearListWidget.inYearActionName}" params="[year: year.year]">${year.year}</g:link>
        </li>
    </g:each>
</ul>
