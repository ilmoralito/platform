package ni.edu.ucc.leon

class UrlMappings {

    static mappings = {
        get "/dashboard/$all?"(controller: 'dashboard', action: 'index')

        get '/login'(controller: 'login', action: 'auth')

        '/coordinations'(resources: 'coordination')

        '/classrooms'(resources: 'classroom')

        '/careers'(resources: 'career')

        '/devices'(resources: 'device')

        '/holidays'(resources: 'holiday')

        '/datashows'(resources: 'datashow', excludes: ['delete'])

        '/roles'(resources: 'role', excludes: ['create'])

        '/employees'(resources: 'employee', excludes: ['delete']) {
            '/bookmarks'(resources: 'bookmark', includes: ['index', 'save', 'delete'])

            '/employeeCoordinations'(resources: 'employeeCoordination', excludes: ['create', 'show'])

            '/tickets'(resources: 'ticket') {
                collection {
                    '/summary'(controller: 'ticket', action: 'summary', method: 'GET')
                }
            }
        }

        group '/employees', {
            // USERS
            "/$employeeId/user/create"(controller: 'user', action: 'create', method: 'GET')
            "/$employeeId/user/$id/edit"(controller: 'user', action: 'edit', method: 'GET')
            "/$employeeId/user/$id"(controller: 'user', action: 'update', method: 'PUT')
            "/$employeeId/user"(controller: 'user', action: 'save', method: 'POST')

            // TICKETS
            "/$employeeId/tickets/summary/$year"(controller: 'ticket', action: 'summaryInYear', method: 'GET')
            "/$employeeId/tickets/summary/$year/month/$month"(controller: 'ticket', action: 'summaryInYearAndMonth', method: 'GET')
        }

        // TICKETS
        // List all open or in progress tickets
        get '/tickets'(controller: 'ticket', action: 'tickets')
        get "/tickets/filter/$status"(controller: 'ticket', action: 'filter')
        get "/tickets/filterByEmployee/$employeeId"(controller: 'ticket', action: 'filterByEmployee')
        post "/tickets/$id/assignment"(controller: 'ticket', action: 'assignment')
        post "/tickets/$id/sendCloseOrder/"(controller: 'ticket', action: 'sendCloseOrder')

        // TASK
        get "/tickets/$ticketId/tasks"(controller: 'task', action: 'index')
        get "/tickets/$ticketId/tasks/create"(controller: 'task', action: 'create')
        post "/tickets/$ticketId/tasks"(controller: 'task', action: 'save')
        get "/tickets/$ticketId/tasks/$id"(controller: 'task', action: 'show')
        get "/tickets/$ticketId/tasks/$id/edit"(controller: 'task', action: 'edit')
        put "/tickets/$ticketId/tasks/$id"(controller: 'task', action: 'update')
        delete "/tickets/$ticketId/tasks/$id"(controller: 'task', action: 'delete')
        post "/tickets/$ticketId/tasks/$id/clone"(controller: 'task', action: 'clone')
        put "/tickets/$ticketId/tasks/$id/updateState"(controller: 'task', action: 'updateState')

        // REPORTS
        group '/reports', {
            get '/summary'(controller: 'report', action: 'summary')
            get "/summary/$year"(controller: 'report', action: 'summaryInYear')
            get "/tickets/status/$year"(controller: 'report', action: 'ticketStatusInYear')
            get "/tickets/status/$year/month/$month"(controller: 'report', action: 'ticketStatusInYearAndMonth')
            get "/tickets/devices/$year"(controller: 'report', action: 'ticketDevicesInYear')
            get "/tickets/devices/$year/month/$month"(controller: 'report', action: 'ticketDevicesInYearAndMonth')
        }

        '/birthday'(controller: 'birthday', action: 'index')

        '/'(controller: 'phoneBook', action: 'index')

        '/login/auth'(controller: 'login', action: 'auth')

        '/logout'(controller: 'logout')

        '500'(view: '/error')

        '404'(view: '/notFound')
    }
}
