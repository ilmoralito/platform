<g:applyLayout name="twoColumns">
    <head>
        <title>Datashow</title>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>

            <tbody>
                <tr>
                    <td>Marca</td>
                    <td>${datashow.trademark}</td>
                </tr>

                <tr>
                    <td>Modelo</td>
                    <td>${datashow.model}</td>
                </tr>

                <tr>
                    <td>Numero de serie</td>
                    <td>${datashow.serialNumber}</td>
                </tr>

                <tr>
                    <td>Codigo</td>
                    <td>${datashow.code}</td>
                </tr>

                <tr>
                    <td>HDMI</td>
                    <td>
                        <platform:yesNo condition="${datashow.hdmi}"/>
                    </td>
                </tr>

                <tr>
                    <td>WIFI</td>
                    <td>
                        <platform:yesNo condition="${datashow.wifi}"/>
                    </td>
                </tr>

                <tr>
                    <td>ETHERNET</td>
                    <td>
                        <platform:yesNo condition="${datashow.ethernet}"/>
                    </td>
                </tr>

                <tr>
                    <td>Habilitado</td>
                    <td>
                        <platform:yesNo condition="${datashow.enabled}"/>
                    </td>
                </tr>
            </tbody>
        </table>

        <g:link resource="${datashow}" action="edit" class="btn btn-primary" method="GET">Editar</g:link>

        <g:link resource="datashow" action="index" method="GET" class="btn btn-default">Regresar</g:link>
    </content>
</g:applyLayout>
