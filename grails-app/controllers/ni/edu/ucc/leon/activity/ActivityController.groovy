package ni.edu.ucc.leon.activity

import com.craigburke.document.builder.PdfDocumentBuilder
import ni.edu.ucc.leon.EmployeeCoordinationService
import grails.validation.ValidationException
import ni.edu.ucc.leon.EmployeeCoordination
import ni.edu.ucc.leon.ActivityService
import ni.edu.ucc.leon.EmployeeService
import ni.edu.ucc.leon.LocationService
import ni.edu.ucc.leon.CustomerService
import ni.edu.ucc.leon.Coordination
import ni.edu.ucc.leon.TableLinen
import ni.edu.ucc.leon.Activity
import ni.edu.ucc.leon.Employee
import ni.edu.ucc.leon.Location
import ni.edu.ucc.leon.Helper

class ActivityController {

    EmployeeCoordinationService employeeCoordinationService
    EmployeeService employeeService
    ActivityService activityService
    LocationService locationService
    CustomerService customerService

    static allowedMethods = [ save: 'POST', update: 'PUT', delete: 'DELETE', sendNotification: 'PUT' ]

    def index(final Long employeeId) {
        respond ([activityList: activityService.list(employeeId)], model: [toolbar: createToolbar(employeeId)])
    }

    def create(final Long employeeId) {
        respond ([activity: new Activity(params)], model: model(employeeId))
    }

    def save(SaveActivityCommand command) {
        if (command.hasErrors()) {
            respond ([errors: command.errors], model: model(command.employeeId), view: 'create')

            return false
        }

        try {
            Activity activity = activityService.save(command)

            flash.message = 'Actividad creada'
            redirect uri: "/employees/${command.employeeId}/activities/${activity.id}", method: 'GET'
        } catch(ValidationException e) {
            respond ([errors: e.errors], model: model(command.employeeId), view: 'create')
        }
    }

    def show(final Long id, final Long employeeId) {
        Activity activity = activityService.find(id)

        [activity: activity]
    }

    def edit(final Long id, final Long employeeId) {
        Employee employee = employeeService.find(employeeId)

        respond ([activity: activityService.find(id)], model: [
            activity: activityService.find(id),
            nameList: activityService.activityNamesPerEmployee(employeeId),
            employeeCoordinations: employeeCoordinationService.listByEmployee(employee),
            customerList: customerService.findAll()
        ])
    }

    def update(UpdateActivityCommand command) {
        if (command.hasErrors()) {
            Map model = model(command.employeeId)

            model['activity'] = activityService.find(id)

            respond ([errors: command.errors], view: 'edit', model: model)

            return
        }

        try {
            Activity activity = activityService.update(command)

            if (!activity) {
                notFound(command.employeeId)

                return
            }

            flash.message = 'Actividad actualizada'
            redirect uri: "/employees/${command.employeeId}/activities/${activity.id}", method: 'GET'
        } catch(ValidationException e) {
            Map model = model(command.employeeId)

            model['activity'] = activityService.find(id)

            respond ([errors: command.errors], model: model, view: 'edit')
        }
    }

    def delete(final Long id, final Long employeeId) {
        Activity activity = activityService.delete(id)

        if (!activity) {
            notFound(employeeId)

            return
        }

        flash.message = 'Actividad eliminada'
        redirect uri: "/employees/$employeeId/activities", method: 'GET'
    }

    def summary(final Long employeeId, final Integer year) {
        List<Map> results = !year ? activityService.getSummary(employeeId) : activityService.getSummary(employeeId, year)

        respond ([results: results], model : [
            yearList: activityService.yearList(employeeId),
            toolbar: createToolbar(employeeId)
        ])
    }

    def sendNotification(final Long employeeId, final Long activityId, final String state) {
        Activity activity = activityService.find(activityId)

        if (activity && activity.locations) {
            activityService.updateState(state ? state : getNextNotificationState(), activityId)

            flash.message = 'Estado actualizado'
        }

        redirect uri: "/employees/$employeeId/activities/$activityId", method: 'GET'
    }

    def requiringAttention(final Long employeeId) {
        final List<Map> results = listRequiringAttention(employeeId)
        final List<Map> activityListByOrganizer = results.groupBy { it.organizer }.collect {
            [organizer: it.key, activities: it.value.collect {
                [id: it.id, name: it.name, locations: it.locations, vouchers: it.vouchers]
            }]
        }

        respond ([activityListByOrganizer: activityListByOrganizer], model: [toolbar: createToolbar(employeeId)])
    }

    def weeklyLogistics(final Long employeeId, final String type) {
        respond ([results: locationService.listWeeklyLogistics(type)], model: [toolbar: createToolbar(employeeId)])
    }

    def printWeeklyLogistics(final Long employeeId, final String type) {
        PdfDocumentBuilder pdfBuilder = new PdfDocumentBuilder(response.outputStream)
        List<Map> logisticList = locationService.listWeeklyLogistics(type)
        Closure template = {
            'document' font: [family: 'Courier', size: 7.pt], margin: [top: 0.3.inches, left: 0.3.inches, right: 0.3.inches]
        }

        pdfBuilder.create {
            document (
                template: template,

                header: { info ->
                    table(border: [size: 0]) {
                        row {
                            cell "UCC LEON - Protocolo - Logistica semanal del ${Helper.FIRST_DAY_OF_WEEK().format('yyyy-MM-dd')} al ${Helper.LAST_DAY_OF_WEEK().format('yyyy-MM-dd')}", align: 'center'
                        }
                    }
                },

                footer: { info ->
                    table(border: [size: 0]) {
                        row {
                            cell 'Elaborado por: Orlando Gaitan Coordinador de protocolo', align: 'center'
                        }
                    }
                }
            ) {
                table(padding: 3.px) {
                    row {
                        cell 'Lugar'
                        cell 'Nombre'
                        cell 'Horario'
                        cell 'Participantes'

                        if (type == 'concierge') {
                            cell 'Montaje'
                        }

                        if (type == 'protocol') {
                            cell 'Manteleria'
                            cell 'B. de agua'
                            cell 'Refrigerios'
                        }

                        cell 'Requerimiento'
                    }

                    logisticList.each { logistic ->
                        row {
                            cell logistic.date, colspan: logistic.locations[0].size() - 1
                        }

                        logistic.locations.each { location ->
                            row {
                                cell location.place
                                cell location.name
                                cell "$location.startTime a $location.endTime"
                                cell location.participants

                                if (type == 'concierge') {
                                    cell location.typeOfAssembly
                                }

                                if (type == 'protocol') {
                                    cell join(in: location.tableLinen)
                                    cell location.waterBottles
                                    cell location?.refreshment?.quantity
                                }

                                cell join(in: location.requirements)
                            }
                        }
                    }
                }
            }
        }

        response.contentType = "application/pdf"
        response.setHeader("Content-disposition", "attachment;filename=test.pdf")
        response.outputStream.flush()
    }

    def reportSummary(final Integer year) {
        List<Map> results = !year ? activityService.getSummary() : activityService.getSummary(year)

        respond ([results: results], model: [yearList: activityService.yearList()], view: '/activity/report/summary')
    }

    def summaryReportDetail(final Integer month, final Integer year) {
        final String format = 'yyyy-MM-dd'
        final List<Map> summary = !year ? activityService.getSummaryDetail(month) : activityService.getSummaryDetail(month, year)
        final List<Map> results = summary.groupBy({ it.coordination }, { Date.parse(format, it.dateCreated).format(format) }).collect {
            [
                coordination: it.key,
                dates: it.value.collect {
                    [
                        dateCreated: it.key,
                        activities: it.value.collect {
                            [id: it.id, name: it.name, state: it.state]
                        }
                    ]
                }
            ]
        }

        respond ([results: results], model: [yearList: activityService.yearList(), employeeId: authenticatedUser.id], view: '/activity/report/summaryDetail')
    }

    protected void notFound(final Long employeeId) {
        flash.message = 'Actividad no encontrada'

        redirect uri: "/employees/$employeeId/activities", method: 'GET'
    }

    private final String getNotificationState() {
        List<String> authorityList = getCurrentUserAuthorityList()

        if ('ROLE_ACADEMIC_COORDINATOR' in authorityList) return 'notified'

        if ('ROLE_ACADEMIC_DIRECTOR' in authorityList) return 'confirmed'

        if ('ROLE_ADMINISTRATIVE_DIRECTOR' in authorityList) return 'approved'

        if ('ROLE_PROTOCOL' in authorityList) return 'authorized'
    }

    private final String getNextNotificationState() {
        List<String> authorityList = getCurrentUserAuthorityList()

        if ('ROLE_ASSISTANT' in authorityList) return 'notified'

        if ('ROLE_ACADEMIC_COORDINATOR' in authorityList) return 'confirmed'

        if ('ROLE_ADMINISTRATIVE_COORDINATOR' in authorityList) return 'notified'

        if ('ROLE_ACADEMIC_DIRECTOR' in authorityList) return 'approved'

        if ('ROLE_ADMINISTRATIVE_DIRECTOR' in authorityList) return 'authorized'

        if ('ROLE_PROTOCOL' in authorityList) return 'notified'
    }

    private final List<String> getCurrentUserAuthorityList() {
        authenticatedUser.authorities.authority
    }

    private final List<Map> listRequiringAttention(final Long employeeId) {
        final String state = getNotificationState()
        List<String> authorities = getCurrentUserAuthorityList()

        if ('ROLE_ADMINISTRATIVE_DIRECTOR' in authorities) return activityService.listRequiringAttention()

        Coordination coordination = Coordination.findByName('Direccion academica')
        if ('ROLE_ACADEMIC_DIRECTOR' in authorities) return activityService.listRequiringAttention(coordination.id)

        if ('ROLE_PROTOCOL' in authorities) return activityService.listRequiringAttention(state)

        if ('ROLE_ACADEMIC_COORDINATOR' in authorities) return activityService.listRequiringAttention(state, employeeId)
    }

    private final Number countRequiringAttention(final Long employeeId) {
        final String state = getNotificationState()
        List<String> authorities = getCurrentUserAuthorityList()

        if ('ROLE_ADMINISTRATIVE_DIRECTOR' in authorities) return activityService.countRequiringAttention()

        if ('ROLE_ACADEMIC_DIRECTOR' in authorities) return activityService.countRequiringAttention(Coordination.findByName('Direccion academica').id)

        if ('ROLE_PROTOCOL' in authorities) return activityService.countRequiringAttention(state)

        if ('ROLE_ACADEMIC_COORDINATOR' in authorities) return activityService.countRequiringAttention(state, employeeId)
    }

    private Toolbar createToolbar(final Long employeeId) {
        new Toolbar (
            notificationNumber: countRequiringAttention(employeeId),
            logisticsTypeList: Helper.LOGISTICS_TYPE_LIST
        )
    }

    private Map model(final Long employeeId) {
        [
            nameList: activityService.activityNamesPerEmployee(employeeId),
            employeeCoordinations: employeeCoordinationService.listByEmployee(employeeService.find(employeeId)),
            customerList: 'ROLE_PROTOCOL' in principal.authorities.authority ? customerService.findAll() : [:]
        ]
    }
}

class Toolbar {
    Integer notificationNumber
    List<LinkedHashMap> logisticsTypeList
}
