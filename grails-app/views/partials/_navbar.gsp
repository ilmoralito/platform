<g:set var="springSecurityService" bean="springSecurityService" scope="page"/>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">UCC LEON</a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <sec:ifLoggedIn>
                    <li class="${controllerName == 'dashboard' ? 'active' : ''}">
                        <a href="/dashboard">Tablero</a>
                    </li>

                    <sec:ifAllGranted roles='ROLE_ADMIN'>
                        <li class="dropdown ${controllerName == 'report' ? 'active' : ''}">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reportes</a>

                            <ul class="dropdown-menu">
                                <li class="${controllerName == 'report' ? 'active' : ''}">
                                    <g:link controller="report" action="summary" method="GET">
                                        Tickets
                                    </g:link>
                                </li>
                            </ul>
                        </li>

                        <platform:bookmarkListCount>
                            <li class="dropdown ${controllerName == 'bookmark' ? 'active' : ''}">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Marcadores</a>

                                <ul class="dropdown-menu">
                                    <platform:bookmarkList/>
                                    <li role="separator" class="divider"></li>
                                    <li class="${controllerName == 'bookmark' ? 'active' : ''}">
                                        <a href="/employees/${springSecurityService.currentUser.employee.id}/bookmarks">Lista de marcadores</a>
                                    </li>
                                </ul>
                            </li>
                        </platform:bookmarkListCount>
                    </sec:ifAllGranted>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                            <sec:loggedInUserInfo field='username'/> <span class="caret"></span>
                        </a>

                        <ul class="dropdown-menu">
                            <li>
                                <a href="#">Perfil</a>
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
</nav>
