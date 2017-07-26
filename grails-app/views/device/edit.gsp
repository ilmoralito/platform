<g:applyLayout name="twoColumns">
    <head>
        <title>Device</title>
    </head>

    <content tag="main">
        <g:form resource="device" action="update" id="${device.id}" autocomplete="off">
            <g:hiddenField name="_method" value="PUT"/>

            <div class="form-group">
                <label for="name">Nombre</label>
                <g:textField name="name" value="${device?.name}" class="form-control"/>
            </div>

            <div class="form-group">
                <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
            </div>
        </g:form>
    </content>
</g:applyLayout>
