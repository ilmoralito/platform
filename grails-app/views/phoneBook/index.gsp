<g:applyLayout name="oneColumn">
    <head>
        <title>Guia telefonica</title>
    </head>

    <content tag="main">
        <g:if test="${phoneBook}">
            <table class="table table-hover">
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
                    <g:each in="${phoneBook}" var="pb" status="index">
                        <tr id="pb_${index}">
                            <td class="triggers" id="${index}">${pb.extensionNumber}</td>
                            <td>${pb.coordination}</td>
                            <td>${pb.manager}</td>
                            <td>${pb.assistants}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>

            <p>
                <small>Haz clic en el número de extension para marcar fila</small>
            </p>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
