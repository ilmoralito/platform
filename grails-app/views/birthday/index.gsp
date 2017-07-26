<g:applyLayout name="oneColumn">
    <head>
        <title>Cumpleañeros del mes</title>
    </head>

    <content tag="main">
        <g:if test="${birthday}">
            <g:if test="${birthdayOfTheToday}">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Cumpleañero del dia
                    </div>

                    <div class="panel-body">
                        <g:each in="${birthdayOfTheToday}" var="b">
                            <p>${b.fullName}</p>
                        </g:each>
                    </div>
                </div>
            </g:if>

            <table class="table table-hover table-striped">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 75%;">
                </colgroup>

                <thead>
                    <tr>
                        <th>Cumpleañeros del mes</th>
                        <th>Dia</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${birthday}" var="b">
                        <tr>
                            <td>${b.fullName}</td>
                            <td>${b.day}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
