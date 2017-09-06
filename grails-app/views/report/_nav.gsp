<ul class="nav nav-tabs">
    <li role="presentation" class="${actionName in ['resume', 'resumeInMonth', 'resumeInYear', 'resumeInYearAndMonth'] ? 'active' : ''}">
        <g:link action="resume">Resumen</g:link>
    </li>
    <li role="presentation" class="${actionName in ['devices', 'devicesInYear'] ? 'active' : ''}">
        <g:link action="devices">Recursos</g:link>
    </li>
    <li role="presentation" class="${actionName in ['state', 'stateInYear'] ? 'active' : ''}">
        <g:link action="state">Estado</g:link>
    </li>
    <li role="presentation" class="${actionName in ['day', 'dayInYear'] ? 'active' : ''}">
        <g:link action="day">Dia</g:link>
    </li>
</ul>