<g:applyLayout name="threeColumns">
    <head>
        <title>Roles</title>
    </head>

    <content tag="main">
        <g:if test="${roleList}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Roles</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${roleList}" var="role">
                        <tr>
                            <td>
                                <g:link resource="${role}" action="show">${role.authority}</g:link>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>

        <g:hasErrors bean="${role}">
            <g:renderErrors bean="${role}"/>
        </g:hasErrors>
    </content>

    <content tag="right">
        <g:form resource="role" action="save" method="POST" autocomplete="off">
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
            </div>
        </g:form>
    </content>
</g:applyLayout>
