<g:applyLayout name="oneColumn">
    <head>
        <title>Guia telefonica</title>
    </head>

    <content tag="main">
        <g:if test="${phoneBook}">
            <table class="table table-hover table-striped">
                <colgroup>
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 40%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Extension</th>
                        <th>Departamento</th>
                        <th>Coordinador</th>
                        <th>Colaboradores</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${phoneBook}" var="pb">
                        <tr>
                            <td>${pb.extensionNumber}</td>
                            <td>${pb.coordination}</td>
                            <td>${pb.manager}</td>
                            <td>${pb.assistants}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
