grails {
    plugin {
        springsecurity {
            securityConfigType = 'InterceptUrlMap'

            userLookup {
                usernamePropertyName = 'email'
                userDomainClassName = 'ni.edu.ucc.leon.User'
                authorityJoinClassName = 'ni.edu.ucc.leon.UserRole'
            }

            logout {
                afterLogoutUrl = '/login/auth'
            }

            authority {
                className = 'ni.edu.ucc.leon.Role'
            }

            filterChain {
                chainMap = [
                    [pattern: '/assets/**',      filters: 'none'],
                    [pattern: '/**/js/**',       filters: 'none'],
                    [pattern: '/**/css/**',      filters: 'none'],
                    [pattern: '/**/images/**',   filters: 'none'],
                    [pattern: '/**/favicon.ico', filters: 'none'],
                    [pattern: '/**',             filters: 'JOINED_FILTERS']
                ]
            }

            successHandler {
                defaultTargetUrl = '/dashboard'
            }

            interceptUrlMap = [
                [pattern: '/',                                  access: ['permitAll']],
                [pattern: '/error',                             access: ['permitAll']],
                [pattern: '/index',                             access: ['permitAll']],
                [pattern: '/index.gsp',                         access: ['permitAll']],
                [pattern: '/shutdown',                          access: ['permitAll']],
                [pattern: '/assets/**',                         access: ['permitAll']],
                [pattern: '/**/js/**',                          access: ['permitAll']],
                [pattern: '/**/css/**',                         access: ['permitAll']],
                [pattern: '/**/images/**',                      access: ['permitAll']],
                [pattern: '/**/favicon.ico',                    access: ['permitAll']],
                [pattern: '/console/**',                        access: ['ROLE_ADMIN']],
                [pattern: '/static/console/**',                 access: ['ROLE_ADMIN']],
                [pattern: '/login/**',                          access: ['permitAll']],
                [pattern: '/login/authfail',                    access: ['permitAll']],
                [pattern: '/logout/**',                         access: ['permitAll']],

                // EMPLOYEE
                [pattern: '/employees/*/tickets/**',            access: ['ROLE_ADMIN', 'ROLE_USER']],
                [pattern: '/employees/**',                      access: ['ROLE_ADMIN']],

                // PHONEBOOK
                [pattern: '/phoneBook',                         access: ['permitAll']],

                // HOLIDAYS
                [pattern: '/holidays/**',                       access: ['ROLE_ADMIN']],

                // BIRTHDAY
                [pattern: '/birthday',                          access: ['permitAll']],

                // BOOKMARKS
                [pattern: '/bookmarks/**',                      access: ['ROLE_ADMIN']],

                // DASHBOARD
                [pattern: '/dashboard/**',                      access: ['ROLE_ADMIN', 'ROLE_USER']],
                [pattern: '/dashboard/index',                   access: ['ROLE_ADMIN', 'ROLE_USER']],

                // TICKETS
                [pattern: '/tickets',                           access: ['ROLE_ADMIN']],
                [pattern: '/tickets/*/assignment',              access: ['ROLE_ADMIN']],
                [pattern: '/tickets/*/resume',                  access: ['ROLE_ADMIN']],
                [pattern: '/tickets/*/swap/*',                  access: ['ROLE_ADMIN']],
                [pattern: '/tickets/filter/*',                  access: ['ROLE_ADMIN']],
                [pattern: '/tickets/filterByEmployee/*',        access: ['ROLE_ADMIN']],
                [pattern: '/tickets/filterByDevice/*',          access: ['ROLE_ADMIN']],
                [pattern: '/tickets',                           access: ['ROLE_ADMIN', 'ROLE_USER']],
                [pattern: '/tickets/create',                    access: ['ROLE_ADMIN', 'ROLE_USER']],
                [pattern: '/tickets/*',                         access: ['ROLE_ADMIN', 'ROLE_USER']],
                [pattern: '/tickets/*/edit',                    access: ['ROLE_ADMIN', 'ROLE_USER']],
                [pattern: '/tickets/update/*',                  access: ['ROLE_ADMIN', 'ROLE_USER']],
                [pattern: '/tickets/delete/*',                  access: ['ROLE_ADMIN', 'ROLE_USER']],
                [pattern: '/tickets/history',                   access: ['ROLE_ADMIN', 'ROLE_USER']],

                // TASKS
                [pattern: '/tickets/*/tasks',                   access: ['ROLE_ADMIN']],
                [pattern: '/tickets/*/tasks/*',                 access: ['ROLE_ADMIN']],
                [pattern: '/tickets/*/tasks/*/edit',            access: ['ROLE_ADMIN']],
                [pattern: '/tickets/*/tasks/*/clone',           access: ['ROLE_ADMIN']],
                [pattern: '/tickets/*/tasks/*/changeState/*',   access: ['ROLE_ADMIN']],

                // DEVICES
                [pattern: '/devices/**',                        access: ['ROLE_ADMIN']],

                // DEVICES
                [pattern: '/datashows/**',                      access: ['ROLE_ADMIN']],

                // COORDINATIONS
                [pattern: '/coordinations/**',                  access: ['ROLE_ADMIN']],

                // CLASSROOMS
                [pattern: '/classrooms/**',                     access: ['ROLE_ADMIN']],

                // REPORTS
                [pattern: '/reports/**',                        access: ['ROLE_ADMIN']],

                // ROLES
                [pattern: '/roles/**',                      access: ['ROLE_ADMIN']],

                // USERS
                [pattern: '/users/**',                      access: ['ROLE_ADMIN', 'ROLE_USER']],

                // CAREERS
                [pattern: '/careers/**',                    access: ['ROLE_ADMIN']],
            ]
        }
    }
}
