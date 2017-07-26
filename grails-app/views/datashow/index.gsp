<g:applyLayout name="twoColumns">
    <head>
        <title>Datashow</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link resource="datashow" action="create" class="btn btn-primary pull-right">Crear datashow</g:link>
        </div>

        <g:if test="${datashowList}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 75%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Modelo</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${datashowList}" var="datashow">
                        <tr>
                            <td>
                                <g:link resource="${datashow}" method="GET">${datashow.code}</g:link>
                            </td>
                            <td>${datashow.trademark}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>

        <g:render template="/partials/errors" model="[errors: flash?.errors]"/>
    </content>
</g:applyLayout>
