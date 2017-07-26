package ni.edu.ucc.leon.report

import ni.edu.ucc.leon.TicketService
import ni.edu.ucc.leon.Ticket

class ReportController {
    @Autowired TicketService ticketService

    static defaultAction = 'summary'

    def summary() {
        [results: ticketService.yearList()]
    }

    def summaryInYear(final Integer year) {
        [results: ticketService.monthList(year)]
    }

    def ticketStatusInYear(final Integer year) {
        render model: [results: ticketService.statusInYear(year)], view: 'ticketStatus'
    }

    def ticketStatusInYearAndMonth(final Integer year, final Integer month) {
        render model: [results: ticketService.statusInYearAndMonth(year, month)], view: 'ticketStatus'
    }

    def ticketDevicesInYear(final Integer year) {
        render model: [results: ticketService.devicesInYear(year)], view: 'ticketDevices'
    }

    def ticketDevicesInYearAndMonth(final Integer year, final Integer month) {
        render model: [results: ticketService.devicesInYearAndMonth(year, month)], view: 'ticketDevices'
    }

    private List<String> MONTHS = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre']
}
