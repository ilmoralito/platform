package ni.edu.ucc.leon.report

import ni.edu.ucc.leon.TicketService
import ni.edu.ucc.leon.Ticket

class ReportController {
    @Autowired TicketService ticketService

    static defaultAction = 'resume'

    def resume() {
        [results: ticketService.resume(), yearListWidget: createYearListWidget('resume', 'resumeInYear')]
    }

    def resumeInYear(final Integer year) {
        render view: 'resume', model: [results: ticketService.resumeInYear(year), yearListWidget: createYearListWidget('resume', 'resumeInYear')]
    }

    def resumeInMonth(final String monthName) {
        render view: 'summary', model: [results: ticketService.resumeInMonth(monthName)]
    }

    def resumeInYearAndMonth(final Integer year, final String monthName) {
        render view: 'summary', model: [results: ticketService.resumeInYearAndMonth(year, monthName)]
    }

    def devices() {
        [results: ticketService.devices(), yearListWidget: createYearListWidget('devices', 'devicesInYear')]
    }

    def devicesInYear(final Integer year) {
        render view: 'devices', model: [results: ticketService.devicesInYear(year), yearListWidget: createYearListWidget('devices', 'devicesInYear')]
    }

    def state() {
        [results: ticketService.state(), yearListWidget: createYearListWidget('state', 'stateInYear')]
    }

    def stateInYear(final Integer year) {
        render view: 'state', model: [results: ticketService.stateInYear(year), yearListWidget: createYearListWidget('state', 'stateInYear')]
    }

    private YearListWidget createYearListWidget(final String globalActionName, final String inYearActionName) {
        new YearListWidget(
            yearList: ticketService.years(),
            globalActionName: globalActionName,
            inYearActionName: inYearActionName
        )
    }

    private List<String> MONTHS = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']
}

class YearListWidget {
    List<Integer> yearList
    String globalActionName
    String inYearActionName
}
