<g:applyLayout name="twoColumns">
    <head>
        <title>Editar usuario</title>
    </head>

    <content tag="main">
        <g:form resource="employee/user" action="update" params="[employeeId: user.employee.id, id: user.id]" method="PUT" autocomplete="off">
            <g:hiddenField name="_method" value="PUT"/>
            <g:render template="form"/>

            <div class="form-group">
                <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
                <g:link resource="${user.employee}" action="show" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>
    </content>
</g:applyLayout>
