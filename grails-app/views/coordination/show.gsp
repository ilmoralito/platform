<g:applyLayout name="twoColumns">
    <head>
        <title>Coordinacion</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td>Nombre de coordinacion</td>
                    <td>${coordination.name}</td>
                </tr>

                <tr>
                    <td>Siglas</td>
                    <td>${coordination.acronym}</td>
                </tr>

                <tr>
                    <td>Extension</td>
                    <td>${coordination.extensionNumber}</td>
                </tr>

                <tr>
                    <td>Cuota de copias</td>
                    <td>${coordination.copyFee}</td>
                </tr>

                <tr>
                    <td>Area</td>
                    <td>
                        <platform:coordinationAreas area="${coordination.area}"/>
                    </td>
                </tr>

                <g:if test="${colorList}">
                    <tr>
                        <td>Colores</td>
                        <td>
                            <g:join in="${colorList.name}"/>
                        </td>
                    </tr>
                </g:if>
            </tbody>
        </table>

        <g:link resource="coordination" action="edit" id="${coordination.id}" class="btn btn-primary">Editar</g:link>
        <a href="#" class="btn btn-danger" onclick="if (confirm('¿Seguro?')) document.getElementById('deleteForm').submit()">Eliminar</a>
        <g:form resource="coordination" action="delete" id="${coordination.id}" name="deleteForm" class="hide">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>
    </content>
</g:applyLayout>
