package ni.edu.ucc.leon.report

import org.apache.commons.csv.CSVPrinter
import org.apache.commons.csv.CSVFormat
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

    def day() {
        [results: ticketService.summaryPerDay(), yearListWidget: createYearListWidget('day', 'dayInYear')]
    }

    def dayInYear(final Integer year) {
        render view: 'day', model: [results: ticketService.summaryPerDayInYear(year), yearListWidget: createYearListWidget('day', 'dayInYear')]
    }

    def export(final String monthName, final Integer year) {
        final List<Map> results = year ? ticketService.resumeInYearAndMonth(year, monthName) : ticketService.resumeInMonth(monthName)
        List<Map> list = []

        withFormat {
            csv {
                results.eachWithIndex { result, index ->
                    if (index == 0) {
                        list << [
                            'Solicitante',
                            'Asunto',
                            'Estado',
                            'Tipo',
                            'Recurso',
                            'Fecha de solicitud',
                            'Actualizacion mas reciente',
                            'Numero de tareas'
                        ]
                    }

                    list << [
                        result.fullName,
                        result.issue,
                        result.status,
                        result.scheduled,
                        result.device,
                        result.dateCreated,
                        result.lastUpdated,
                        result.tasks
                    ]
                }

                StringWriter stringWriter = new StringWriter();
                CSVPrinter printer = new CSVPrinter(stringWriter, CSVFormat.EXCEL);
                printer.printRecords(list)
                printer.flush()
                printer.close()
                final String result = stringWriter.toString()

                response.setHeader('Content-disposition', 'attachment; filename=sumario_de_actividades.csv')
                render(contentType: 'text/csv', text: result)
            }
        }
    }

    private YearListWidget createYearListWidget(final String globalActionName, final String inYearActionName) {
        new YearListWidget(
            yearList: ticketService.years(),
            globalActionName: globalActionName,
            inYearActionName: inYearActionName
        )
    }
}

class YearListWidget {
    List<Integer> yearList
    String globalActionName
    String inYearActionName
}
