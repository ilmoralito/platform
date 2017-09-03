<ul class="nav nav-tabs">
    <li role="presentation" class="${actionName == 'profile' ? 'active' : ''}">
        <g:link action="profile">Perfil</g:link>
    </li>
    <li role="presentation" class="${actionName in ['password', 'changePassword'] ? 'active' : ''}">
        <g:link action="password">Cambiar clave de paso</g:link>
    </li>
</ul>
