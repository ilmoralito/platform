<g:applyLayout name="twoColumns">
    <head>
        <title>Aulas</title>
    </head>

    <content tag="main">
        <div class="clearfix">
            <g:link action="create" class="btn btn-primary pull-right">Crear aula</g:link>
        </div>

        <g:if test="${classroomList}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 75%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Nombre</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${classroomList}" var="classroom">
                        <tr>
                            <td>
                                <g:link resource="classroom" action="show" id="${classroom.id}">${classroom.code}</g:link>
                            </td>
                            <td>${classroom.name}</td>
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
