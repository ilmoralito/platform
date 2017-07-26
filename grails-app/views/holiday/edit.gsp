<g:applyLayout name="twoColumns">
    <head>
        <title>Editar dia feriado</title>
    </head>

    <content tag="main">
        <g:form resource="holiday" action="update" id="${holiday.id}" autocomplete="off">
            <g:hiddenField name="_method" value="PUT"/>

            <div class="form-group">
                <label for="date">Fecha</label>
                <g:datePicker name="date" precision="day" years="${1850..1990}" value="${holiday?.date}"/>
            </div>

            <div class="form-group">
                <label for="name">Nombre</label>
                <g:textField name="name" value="${holiday?.name}" class="form-control"/>
            </div>

            <div class="form-group">
                <label for="wiki">Wiki</label>
                <g:textField name="wiki" value="${holiday?.wiki}" class="form-control"/>
            </div>

            <div class="form-group">
                <button type="submit" name="send" class="btn btn-primary">Actualizar</button>
                <g:link resource="${holiday}" action="show" method="GET" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${holiday}">
            <g:renderErrors bean="${holiday}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
