<g:applyLayout name="threeColumns">
    <head>
        <title>Devices</title>
    </head>

    <content tag="main">
        <g:if test="${deviceList}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Recurso</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${deviceList}" var="device">
                        <tr>
                            <td>
                                <g:link resource="device" action="show" id="${device.id}">${device.name}</g:link>
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

    <content tag="right">
        <g:form resource="device" action="save" autocomplete="off">
            <div class="form-group">
                <label for="name">Nombre</label>
                <g:textField name="name" class="form-control"/>
            </div>

            <div class="form-group">
                <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
            </div>
        </g:form>
    </content>
</g:applyLayout>
