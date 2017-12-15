<g:applyLayout name="threeColumns">
    <head>
        <title>Lista de visitas</title>
    </head>

    <content tag="main">
        <g:if test="${guestList}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Visitas</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${guestList}" var="guest">
                        <tr>
                            <td>
                                <g:link resource="${guest}" method="GET">${guest.fullName}</g:link>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>

    <content tag="right">
        <g:form resource="guest" action="save" method="POST" autocomplete="off">
            <div class="form-group">
                <label for="fullName">Nombre y apellido</label>
                <g:textField name="fullName" class="form-control"/>
            </div>

            <div class="form-group">
                <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
            </div>
        </g:form>

        <g:hasErrors bean="${guest}">
            <g:renderErrors bean="${guest}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
