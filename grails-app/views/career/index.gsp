<g:applyLayout name="twoColumns">
    <head>
        <title>Carrera</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link action="create" class="btn btn-primary pull-right">Crear carrera</g:link>
        </div>

        <g:if test="${careerList}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Carreras</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${careerList}" var="career">
                        <tr>
                            <td>
                                <g:link action="show" id="${career.id}">${career.name}</g:link>
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
