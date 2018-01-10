<g:set var="springSecurityService" bean="springSecurityService" scope="page"/>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#nami" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <sec:ifNotLoggedIn>
                    <a class="navbar-brand" href="/">UCC LEON</a>
                </sec:ifNotLoggedIn>
                <sec:ifLoggedIn>
                    <a class="navbar-brand" href="/dashboard">UCC LEON</a>
                </sec:ifLoggedIn>
            </div>

            <div class="collapse navbar-collapse" id="nami">
                <ul class="nav navbar-nav navbar-right">
                    <sec:ifLoggedIn>
                        <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_PROTOCOL'>
                            <li class="dropdown ${controllerName in ['report', 'activity', 'fixedVoucher'] && actionName in ['report', 'reportSummary', 'summaryReportDetail'] ? 'active' : ''}">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reportes</a>

                                <ul class="dropdown-menu">
                                    <sec:ifAllGranted roles="ROLE_ADMIN">
                                        <li class="${controllerName == 'report' ? 'active' : ''}">
                                            <g:link controller="report" method="GET">
                                                Tickets
                                            </g:link>
                                        </li>
                                    </sec:ifAllGranted>

                                    <sec:ifAllGranted roles="ROLE_PROTOCOL">
                                        <li class="${controllerName == 'activity' && actionName in ['reportSummary'] ? 'active' : ''}">
                                            <g:link resource="activity" action="reportSummary" method="GET">
                                                Actividades
                                            </g:link>
                                        </li>

                                        <li class="${controllerName == 'fixedVoucher' && actionName in ['report'] ? 'active' : ''}">
                                            <g:link resource="fixedVoucher" action="report" method="GET">
                                                Vales fijos
                                            </g:link>
                                        </li>
                                    </sec:ifAllGranted>
                                </ul>
                            </li>
                        </sec:ifAnyGranted>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                <sec:loggedInUserInfo field='username'/> <span class="caret"></span>
                            </a>

                            <ul class="dropdown-menu">
                                <li class="${actionName in ['profile', 'password', 'changePassword'] ? 'active' : ''}">
                                    <g:link controller="user" action="profile">Perfil</g:link>
                                </li>
                                <li role="separator" class="divider"></li>
                                <li>
                                    <a href="#" onclick="document.getElementById('logoutForm').submit()">Salir</a>

                                    <g:form controller="logout" name="logoutForm" style="display: none"></g:form>
                                </li>
                            </ul>
                        </li>
                    </sec:ifLoggedIn>

                    <sec:ifNotLoggedIn>
                        <li class="${controllerName == 'phoneBook' ? 'active' : ''}">
                            <g:link resource="phoneBook" action="index">
                                Guia telefonica
                            </g:link>
                        </li>
                        <li class="${controllerName == 'birthday' ? 'active' : ''}">
                            <g:link resource="birthday" action="index">
                                Cumpleañeros
                                <platform:areThereAnyBirthdayPartiesToday>
                                    <span class="glyphicon glyphicon-bell" aria-hidden="true"></span>
                                </platform:areThereAnyBirthdayPartiesToday>
                            </g:link>
                        </li>
                        <li class="${controllerName == 'login' ? 'active' : ''}">
                            <a href="/login">Iniciar sesión</a>
                        </li>
                    </sec:ifNotLoggedIn>
                </ul>
            </div>
        </div>
    </div>
</nav>
