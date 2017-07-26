<g:applyLayout name="twoColumns">
    <head>
        <title>Crear dia feriado</title>
    </head>

    <content tag="main">
        <g:form resource="holiday" action="save" method="POST" autocomplete="off">
            <div class="form-group">
                <label for="date">Fecha</label>
                <g:datePicker name="date" precision="day" years="${1850..1990}"/>
            </div>

            <div class="form-group">
                <label for="name">Nombre</label>
                <g:textField name="name" class="form-control"/>
            </div>

            <div class="form-group">
                <label for="wiki">Wiki</label>
                <g:textField name="wiki" class="form-control"/>
            </div>

            <div class="form-group">
                <button type="submit" name="send" class="btn btn-primary">Agregar</button>
            </div>
        </g:form>

        <g:hasErrors bean="${holiday}">
            <g:renderErrors bean="${holiday}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
