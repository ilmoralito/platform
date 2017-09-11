<g:applyLayout name="twoColumns">
    <head>
        <title>Tickets</title>
    </head>

    <content tag="main">
        <g:form controller="ticket" action="applyFilter" method="POST" autocomplete="off">

            <details>
                <summary>Estado</summary>
                <g:each in="${statesList}" var="state">
                    <div class="form-group">
                        <div class="checkbox">
                            <label for="${state.english}">
                                <g:checkBox name="states" value="${state.english}" checked="false"/>
                                ${state.spanish}
                            </label>
                        </div>
                    </div>
                </g:each>
            </details>

            <details>
                <summary>Recursos</summary>
                <g:each in="${deviceList}" var="device">
                    <div class="form-group">
                        <div class="checkbox">
                            <label for="${device.name}">
                                <g:checkBox name="devices" value="${device.name}" checked="false"/>
                                ${device.name}
                            </label>
                        </div>
                    </div>
                </g:each>
            </details>

            <details>
                <summary>Empleados</summary>
                <g:each in="${employeeList}" var="employee">
                    <div class="form-group">
                        <div class="checkbox">
                            <label for="${employee.fullName}">
                                <g:checkBox name="employees" value="${employee.id}" checked="false"/>
                                ${employee.fullName}
                            </label>
                        </div>
                    </div>
                </g:each>
            </details>

            <g:submitButton name="send" value="Filtrar" class="btn btn-primary"/>
        </g:form>
    </content>
</g:applyLayout>
