<table class="table table-hover table-bordered">
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
            <g:if test="${pb.coordinations.size() == 1}">
                <tr id="pb_${index}">
                    <td class="text-center triggers" id="${index}" rowspan="${pb.coordinations.size()}" style="vertical-align: middle;">
                        ${pb.extensionNumber}
                    </td>
                    <td>${pb.coordinations[0].name}</td>
                    <td>${pb.coordinations[0].manager}</td>
                    <td>${pb.coordinations[0].assistants}</td>
                </tr>
            </g:if>
            <g:else>
                <g:each in="${pb.coordinations}" var="coordination" status="status">
                    <tr class="pb_${index}">
                        <g:if test="${status == 0}">
                            <td rowspan="${pb.coordinations.size()}" id="${status}" class="text-center triggers" style="vertical-align: middle;">
                                ${pb.extensionNumber}
                            </td>
                        </g:if>
                        <td>${coordination.name}</td>
                        <td>${coordination.manager}</td>
                        <td>${coordination.assistants}</td>
                    </tr>
                </g:each>
            </g:else>
        </g:each>
    </tbody>
</table>
