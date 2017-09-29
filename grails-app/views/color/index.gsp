<g:applyLayout name="threeColumns">
    <head>
        <title>Lista de colores</title>
    </head>

    <content tag="main">
        <g:if test="${colorList}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Colores</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${colorList}" var="color">
                        <tr>
                            <td>
                                <g:link resource="${color}" method="GET">${color.name}</g:link>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>

    <content tag="right">
        <g:form resource="color" action="save" method="POST" autocomplete="off">
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
            </div>
        </g:form>

        <g:hasErrors bean="${color}">
            <g:renderErrors bean="${color}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
