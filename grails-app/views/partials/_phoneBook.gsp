<table class="table table-hover table-bordered" style="margin-top: 0;">
    <col width="2%">
    <col width:="25">
    <col width:="25">
    <col width:="48">

    <thead>
        <tr>
            <th class="text-center">Ext</th>
            <th>Departamento</th>
            <th>Coordinador</th>
            <th>Colaboradores</th>
        </tr>
    </thead>

    <tbody>
        <g:each in="${phoneBook}" var="pb" status="index">
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
        </g:each>
    </tbody>
</table>
