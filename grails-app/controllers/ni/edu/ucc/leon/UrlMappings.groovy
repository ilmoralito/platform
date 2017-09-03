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
            '/updateFullName'(controller: 'employee', action: 'updateFullName', method: 'PUT')

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
        post "/tickets/$id/swap/$status"(controller: 'ticket', action: 'swap')
        get "/tickets/$id/resume/"(controller: 'ticket', action: 'resume')

        // TASK
        get "/tickets/$ticketId/tasks"(controller: 'task', action: 'index')
        get "/tickets/$ticketId/tasks/create"(controller: 'task', action: 'create')
        post "/tickets/$ticketId/tasks"(controller: 'task', action: 'save')
        get "/tickets/$ticketId/tasks/$id"(controller: 'task', action: 'show')
        get "/tickets/$ticketId/tasks/$id/edit"(controller: 'task', action: 'edit')
        put "/tickets/$ticketId/tasks/$id"(controller: 'task', action: 'update')
        delete "/tickets/$ticketId/tasks/$id"(controller: 'task', action: 'delete')
        get "/tickets/$ticketId/tasks/$id/clone"(controller: 'task', action: 'clone')
        get "/tickets/$ticketId/tasks/$id/changeState/$state"(controller: 'task', action: 'changeState')

        // REPORTS
        group '/reports', {
            get '/' (controller: 'report', action: 'resume')
            get "/$year" (controller: 'report', action: 'resumeInYear')
            get "/month/$monthName" (controller: 'report', action: 'resumeInMonth')
            get "/$monthName/$year" (controller: 'report', action: 'resumeInYearAndMonth')
            get '/devices' (controller: 'report', action: 'devices')
            get "/devices/$year" (controller: 'report', action: 'devicesInYear')
            get '/state' (controller: 'report', action: 'state')
            get "/state/$year" (controller: 'report', action: 'stateInYear')
            get "/export/$monthName"(controller: 'report', action: 'export')
            get "/export/$year/$monthName"(controller: 'report', action: 'export')
        }

        // USER
        group '/user', {
            get '/profile' (controller: 'user', action: 'profile')
            get '/password' (controller: 'user', action: 'password')
            post '/changePassword' (controller: 'user', action: 'changePassword')
        }

        '/birthday'(controller: 'birthday', action: 'index')

        '/'(controller: 'phoneBook', action: 'index')

        '/login/auth'(controller: 'login', action: 'auth')

        '/logout'(controller: 'logout')

        '403'(view: '/forbidden')

        '404'(view: '/notFound')

        '500'(view: '/error')
    }
}
