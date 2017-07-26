<div class="form-group">
    <label for="email">Correo institucional</label>
    <g:textField name="email" value="${user?.email}" class="form-control"/>
</div>

<g:if test="${actionName == 'edit'}">
    <div class="form-group">
        <div class="checkbox">
            <label>
                <g:checkBox name="enabled" value="${user?.enabled}"/>
                Estado
            </label>
        </div>
    </div>
</g:if>

<p>Roles</p>
<g:each in="${roleList}" var="role">
    <div class="form-group">
        <div class="checkbox">
            <label>
                <g:if test="${actionName == 'create'}">
                    <g:checkBox name="roles" value="${role.id}" checked="false"/>
                </g:if>
                <g:else>
                    <g:checkBox name="roles" value="${role.id}" checked="${role.id in user?.authorities?.id}"/>
                </g:else>
                ${role.authority.replaceAll('ROLE_', '')}
            </label>
        </div>
    </div>
</g:each>
