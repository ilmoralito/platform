<g:applyLayout name="oneColumnTiny">
    <head>
        <title>Filtrar</title>
    </head>

    <content tag="main">
        <g:form resource="fixedVoucher" action="applyFilter" method="POST" autocomplete="off">
            <div class="form-group">
                <label for="sinceDate">Desde la fecha</label>

                <g:textField name="sinceDate" class="form-control"/>
            </div>

            <div class="form-group">
                <label for="tillDate">Hasta la fecha</label>

                <g:textField name="tillDate" class="form-control"/>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Aplicar filtro</button>

                <g:link resource="fixedVoucher" action="index" class="btn btn-default">Regresar</g:link>
            </div>
        </g:form>

        <g:hasErrors bean="${errors}">
            <g:renderErrors bean="${errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
