<g:applyLayout name="twoColumns">
    <head>
        <title>Vale fijo</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <col width="25%">
            <col width="75%">

            <tbody>
                <tr>
                    <td>Fecha</td>
                    <td>
                        <g:formatDate date="${fixedVoucher.date}" format="yyyy-MM-dd"/>
                    </td>
                </tr>

                <tr>
                    <td>A nombre de</td>
                    <td>${fixedVoucher.employee.fullName}</td>
                </tr>

                <tr>
                    <td>Coordinacion</td>
                    <td>${fixedVoucher.coordination.name}</td>
                </tr>

                <tr>
                    <td>Precio</td>
                    <td>${fixedVoucher.price}</td>
                </tr>

                <tr>
                    <td>Servicios</td>
                    <td>
                        <g:join in="${serviceList}"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">META</td>
                </tr>

                <tr>
                    <td>Fecha de creacion</td>
                    <td>
                        <g:formatDate date="${fixedVoucher.dateCreated}" format="yyyy-MM-dd HH:mm"/>
                    </td>
                </tr>

                <tr>
                    <td>Mas reciente actualizacion</td>
                    <td>
                        <g:formatDate date="${fixedVoucher.lastUpdated}" format="yyyy-MM-dd HH:mm"/>
                    </td>
                </tr>
            </tbody>
        </table>

        <g:link resources="fixedVoucher" action="edit" id="${fixedVoucher.id}" method="GET" class="btn btn-primary">Editar</g:link>

        <a href="#" onclick="if (confirm('¿Seguro?')) document.querySelector('#deleteForm').submit();" class="btn btn-danger">Eliminar</a>

        <g:link resources="fixedVoucher" action="index" params="[date: fixedVoucher.date.format('yyyy-MM-dd')]" method="GET" class="btn btn-default">Regresar</g:link>

        <g:form name="deleteForm" resources="fixedVoucher" action="delete" id="${fixedVoucher.id}" method="DELETE">
            <g:hiddenField name="_method" value="DELETE"/>
        </g:form>
    </content>
</g:applyLayout>
