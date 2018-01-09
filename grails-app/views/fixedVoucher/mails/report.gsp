<%@ page contentType="text/html"%>

<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <style type="text/css">
            body {
                padding-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="col-md-12">
                <g:if test="${year}">
                    <p>Resultados del mes ${month} en ${year}</p>
                </g:if>
                <g:else>
                    <p>Resultados del mes ${month}</p>
                </g:else>

                <g:render template="report" model="[fixedVoucherList: fixedVoucherList]"/>

                <g:render
                    template="summary"
                    model="[caption: 'Sumario por area', label: 'Area', summaryList: model.areaSummary]"/>

                <g:render
                    template="summary"
                    model="[caption: 'Sumario por coordinacion', label: 'Coordinacion', summaryList: model.coordinationSummary]"/>

                <g:render
                    template="summary"
                    model="[caption: 'Sumario por empleado', label: 'Empleado', summaryList: model.employeeSummary]"/>
            </div>
        </div>
    </body>
</html>
