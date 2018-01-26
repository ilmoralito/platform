package ni.edu.ucc.leon

class UrlMappings {

    static mappings = {
        get "/dashboard/$all?"(controller: 'dashboard', action: 'index')

        get '/login'(controller: 'login', action: 'auth')

        '/coordinations'(resources: 'coordination')

        '/classrooms'(resources: 'classroom')

        '/careers'(resources: 'career')

        '/colors'(resources: 'color', excludes: ['create'])

        '/devices'(resources: 'device')

        '/holidays'(resources: 'holiday')

        '/datashows'(resources: 'datashow', excludes: ['delete'])

        '/roles'(resources: 'role', excludes: ['create'])

        '/customers'(resources: 'customer')

        '/guests'(resources: 'guest', excludes: ['create'])

        '/coffeeShops'(resources: 'coffeeShop', excludes: ['create'])

        "/activities/report/summary/$year?"(controller: 'activity', action: 'reportSummary')

        "/activities/report/summary/detail/$month/$year?"(controller: 'activity', action: 'summaryReportDetail')

        "/employees/$employeeId/guests/$coordination?"(controller: 'employee', action: 'guests', method: 'GET')
        "/employees/$employeeId/guests"(controller: 'employee', action: 'store', method: 'POST')

        // FIXED VOUHCER
        get "/fixed/vouchers/since/$sinceDate/til/$tillDate"(controller: 'fixedVoucher', action: 'applyFilter')
        get '/fixed/vouchers'(controller: 'fixedVoucher', action: 'index')
        get "/fixed/vouchers/create/$date?"(controller: 'fixedVoucher', action: 'create')
        post '/fixed/vouchers'(controller: 'fixedVoucher', action: 'save')
        get "/fixed/vouchers/$id"(controller: 'fixedVoucher', action: 'show')
        get "/fixed/vouchers/$id/edit"(controller: 'fixedVoucher', action: 'edit')
        put "/fixed/vouchers/$id"(controller: 'fixedVoucher', action: 'update')
        delete "/fixed/vouchers/$id"(controller: 'fixedVoucher', action: 'delete')
        get '/fixed/vouchers/print'(controller: 'fixedVoucher', action: 'print')
        get '/fixed/vouchers/filter'(controller: 'fixedVoucher', action: 'filter')
        post '/fixed/vouchers/apply/filter'(controller: 'fixedVoucher', action: 'applyFilter')
        get "/fixed/vouchers/report/$year?"(controller: 'fixedVoucher', action: 'report')
        get "/fixed/vouchers/report/$month/summary/$year?"(controller: 'fixedVoucher', action: 'reportSummary')
        get "/fixed/vouchers/send/report/$month/summary/$year?"(controller: 'fixedVoucher', action: 'sendReport')

        // VOUCHER
        get "/employees/$employeeId/activities/$activityId/vouchers/$type" {
            controller = 'voucher'
            action = 'index'
            constraints {
                type inList: ['employee', 'guest']
            }
        }
        post "/employees/$employeeId/activities/$activityId/vouchers/$type"(controller: 'voucher', action: 'save')
        post "/employees/$employeeId/activities/$activityId/guestvouchers/$type"(controller: 'voucher', action: 'store')
        get "/employees/$employeeId/activities/$activityId/vouchers/$id"(controller: 'voucher', action: 'show')
        get "/employees/$employeeId/activities/$activityId/vouchers/$id/edit"(controller: 'voucher', action: 'edit')
        delete "/employees/$employeeId/activities/$activityId/vouchers/$id"(controller: 'voucher', action: 'delete')
        get "/employeeList/$date/$activityId"(controller: 'voucher', action: 'employeeList')
        get "/guestList/$date/$activityId"(controller: 'voucher', action: 'guestList')
        get "/print/$activityId/vouchers"(controller: 'voucher', action: 'print')

        '/employees'(resources: 'employee', excludes: ['delete']) {
            '/updateFullName'(controller: 'employee', action: 'updateFullName', method: 'PUT')
            '/employeeCoordinations'(resources: 'employeeCoordination', excludes: ['create', 'show'])

            '/tickets'(resources: 'ticket') {
                collection {
                    '/summary'(controller: 'ticket', action: 'summary', method: 'GET')
                }
            }

            '/activities'(resources: 'activity') {
                '/observations'(resources: 'observation')
                // '/vouchers'(resources: 'voucher', excludes: ['create'])
                "/notify/$state?"(controller: 'activity', action: 'sendNotification', method: 'PUT')

                collection {
                    "/summary/$year?"(controller: 'activity', action: 'summary', method: 'GET')
                    '/requiring/attention'(controller: 'activity', action: 'requiringAttention', method: 'GET')
                    "/weekly/logistics/$type"(controller: 'activity', action: 'weeklyLogistics', method: 'GET')
                    "/print/weekly/logistics/$type"(controller: 'activity', action: 'printWeeklyLogistics', method: 'GET')
                }

                '/locations'(resources: 'location') {
                    '/clone'(controller: 'location', action: 'clone', method: 'GET')
                    '/showAll'(controller: 'location', action: 'showAll', method: 'GET')
                    '/refreshments'(resources: 'refreshment', includes: ['create', 'save', 'edit', 'update', 'delete'])
                }
            }
        }

        group '/bookmarks', {
            '/' (controller: 'ticketBookmark', action: 'index', method: 'GET')
            '/toggle' (controller: 'ticketBookmark', action: 'toggle', method: 'POST')
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
        group '/tickets', {
            get '/'(controller: 'ticket', action: 'tickets')

            get '/filter'(controller: 'ticket', action: 'filter')
            post '/filter'(controller: 'ticket', action: 'applyFilter')

            get "/status/$status"(controller: 'ticket', action: 'filterByStatus')
            get "/employee/$employeeId"(controller: 'ticket', action: 'filterByEmployee')
            get "/device/$name"(controller: 'ticket', action: 'filterByDevice')

            post "/$id/assignment"(controller: 'ticket', action: 'assignment')
            post "/$id/swap/$status"(controller: 'ticket', action: 'swap')
            get "/$id/resume/"(controller: 'ticket', action: 'resume')

            // TASK
            get "/$ticketId/tasks"(controller: 'task', action: 'index')
            get "/$ticketId/tasks/create"(controller: 'task', action: 'create')
            post "/$ticketId/tasks"(controller: 'task', action: 'save')
            get "/$ticketId/tasks/$id"(controller: 'task', action: 'show')
            get "/$ticketId/tasks/$id/edit"(controller: 'task', action: 'edit')
            put "/$ticketId/tasks/$id"(controller: 'task', action: 'update')
            delete "/$ticketId/tasks/$id"(controller: 'task', action: 'delete')
            get "/$ticketId/tasks/$id/clone"(controller: 'task', action: 'clone')
            get "/$ticketId/tasks/$id/changeState/$state"(controller: 'task', action: 'changeState')
        }

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
            get '/day' (controller: 'report', action: 'day')
            get "/dayInYear" (controller: 'report', action: 'dayInYear')
            get "/export/$monthName"(controller: 'report', action: 'export')
            get "/export/$year/$monthName"(controller: 'report', action: 'export')
        }

        // USER
        group '/user', {
            get '/profile' (controller: 'user', action: 'profile')
            get '/password' (controller: 'user', action: 'password')
            post '/changePassword' (controller: 'user', action: 'changePassword')
        }

        //  ACTIVITIES
        // '/activities/requiring/attention'(controller: 'activity', action: 'requiringAttention', method: 'GET')

        '/birthday'(controller: 'birthday', action: 'index')

        '/'(controller: 'phoneBook', action: 'index')

        '/login/auth'(controller: 'login', action: 'auth')

        '/logout'(controller: 'logout')

        '403'(view: '/forbidden')

        '404'(view: '/notFound')

        '500'(view: '/error')
    }
}
