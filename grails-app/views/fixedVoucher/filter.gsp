<g:applyLayout name="oneColumnTiny">
    <head>
        <title>Filtrar</title>
    </head>

    <content tag="main">
        <h4 class="text-center">Filtrar vales vijos</h4>

        <form action="${createLink(controller: 'fixedVoucher', action: 'applyFilter')}" method="get" autocomplete="off">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="sinceDate">Desde la fecha</label>

                        <g:textField name="sinceDate" class="form-control"/>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="tillDate">Hasta la fecha</label>

                        <g:textField name="tillDate" class="form-control"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Aplicar filtro</button>

                <g:link resource="fixedVoucher" action="index" class="btn btn-default">Regresar</g:link>
            </div>
        </form>

        <g:hasErrors bean="${errors}">
            <g:renderErrors bean="${errors}"/>
        </g:hasErrors>
    </content>
</g:applyLayout>
