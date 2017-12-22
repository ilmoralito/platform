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

            <table class="table table-hover table-bordered">
                <col width="1%;">
                <col width="99%;">

                <thead>
                    <tr>
                        <th>Dia</th>
                        <th>Cumpleañeros</th>
                    </tr>
                </thead>

                <tbody>
                    <g:each in="${birthday}" var="b">
                        <tr>
                            <td rowspan="${b.birthdayList.size() + 1}" class="text-center" style="vertical-align: middle;">${b.day}</td>
                        </tr>

                        <g:each in="${b.birthdayList}" var="person">
                            <tr>
                                <td>${person}</td>
                            </tr>
                        </g:each>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
