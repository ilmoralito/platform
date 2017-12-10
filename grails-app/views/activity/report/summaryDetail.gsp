<g:applyLayout name="threeColumns">
    <head>
        <title>Detalle de resumen</title>
    </head>

    <content tag="main">
        <table class="table table-hover table-bordered" style="margin-top: 0;">
            <caption>Resultados</caption>

            <col width="25%">
            <col width="70%">
            <col width="5%">

            <tbody>
                <g:each in="${results}" var="result">
                    <tr>
                        <td colspan="3">${result.coordination}</td>
                    </tr>

                    <g:each in="${result.dates}" var="date">
                        <tr>
                            <td rowspan="${date.activities.size() + 1}">${date.dateCreated}</td>
                        </tr>

                        <g:each in="${date.activities}" var="activity">
                            <tr>
                                <td>
                                    <g:if test="${activity.state in ['authorized', 'attended', 'canceled']}">
                                        <g:link
                                            resource="employee/activity"
                                            employeeId="${employeeId}"
                                            id="${activity.id}"
                                            action="show"
                                            method="GET">
                                            ${activity.name}
                                        </g:link>
                                    </g:if>
                                    <g:else>
                                        ${activity.name}
                                    </g:else>
                                </td>
                                <td>
                                    <activity:state currentState="${activity.state}"/>
                                </td>
                            </tr>
                        </g:each>
                    </g:each>
                </g:each>
            </tbody>
        </table>
    </content>

    <content tag="right">
        <g:render template="report/yearList"/>
    </content>
</g:applyLayout>
