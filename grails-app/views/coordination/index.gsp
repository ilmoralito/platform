<g:applyLayout name="twoColumns">
    <head>
        <title>Coordinaciones</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link action="create" class="btn btn-primary pull-right">Crear coordinacion</g:link>
        </div>

        <g:if test="${coordinationList}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Coordinacion</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${coordinationList}" var="coordination">
                        <tr>
                            <td>
                                <g:link action="show" id="${coordination.id}">${coordination.name}</g:link>
                            </td>
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
