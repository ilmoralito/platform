package ni.edu.ucc.leon.location

import ni.edu.ucc.leon.CoordinationColorService
import grails.validation.ValidationException
import ni.edu.ucc.leon.ClassroomService
import ni.edu.ucc.leon.LocationService
import ni.edu.ucc.leon.ActivityService
import ni.edu.ucc.leon.ColorService
import ni.edu.ucc.leon.TableLinen
import ni.edu.ucc.leon.Location
import ni.edu.ucc.leon.Activity
import ni.edu.ucc.leon.Helper

class LocationController {

    CoordinationColorService coordinationColorService
    ClassroomService classroomService
    LocationService locationService
    ActivityService activityService
    ColorService colorService

    static allowedMethods = [ save: 'POST', update: 'PUT', delete: 'DELETE' ]

    def index(final Long activityId, final Long employeeId) {
        respond locationService.findAllByActivity(activityId), model: [activity: activityService.find(activityId)]
    }

    def create(final Long activityId, final Long employeeId) {
        respond new Location(params), model: [
            hourList: Helper.HOURS,
            montageList: Helper.MONTAGE,
            colorList: colorService.findAll(),
            classroomList: classroomService.groupedByCode(),
            organizerColorList: coordinationColorService.listColorsByCoordination(activityService.find(activityId).organizedBy)
        ]
    }

    def save(SaveLocationCommand command) {
        if (command.hasErrors()) {
            // respond ([errors: command.errors], model: [
            //     hourList: Helper.HOURS,
            //     montageList: Helper.MONTAGE,
            //     colorList: colorService.findAll(),
            //     classroomList: classroomService.groupedByCode(),
            //     organizerColorList: coordinationColorService.listColorsByCoordination(activityService.find(command.activityId).organizedBy)
            // ], view: 'create')

            flash.message = 'Parametros incorretos'
            redirect uri: request.getHeader('referer')

            return
        }

        try {
            Location location = locationService.save(
                command.activityId,
                command.place,
                Helper.setTimeToDate(command.startDateAndTime, command.interval.first()),
                Helper.setTimeToDate(command.startDateAndTime, command.interval.last()),
                command.participants,
                command.typeOfAssembly,
                command.colors,
                command.datashow,
                command.podium,
                command.displayTable,
                command.flags,
                command.water,
                command.coffee,
                command.nationalAnthem,
                command.universityAnthem,
                command.triumphalAnthem,
                command.sound,
                command.projectorTable,
                command.waterBottles,
                command.observation
            )

            flash.message = 'Ubicacion creada'
            redirect uri: "/employees/${command.employeeId}/activities/${command.activityId}/locations/${location.id}", method: 'GET'
        } catch(ValidationException e) {
            respond ([errors: e.errors], model: [
                hourList: Helper.HOURS,
                montageList: Helper.MONTAGE,
                colorList: colorService.findAll(),
                classroomList: classroomService.groupedByCode(),
                organizerColorList: coordinationColorService.listColorsByCoordination(activityService.find(command.activityId).organizedBy)
            ], view: 'create')
        }
    }

    def show(final Long id) {
        Location location = locationService.find(id)

        if (!location) response.sendError 404

        Map<String, Object> generalData = location.properties.subMap(['place', 'startDateAndTime', 'endDateAndTime', 'participants'])
        Map<String, Object> mounting = location.properties.subMap(['typeOfAssembly', 'podium', 'displayTable', 'flags', 'projectorTable']).findAll { it.value }
        Map<String, Object> requirements = location.properties.subMap(['datashow', 'nationalAnthem', 'universityAnthem', 'triumphalAnthem', 'sound', 'water', 'coffee', 'waterBottles']).findAll { it.value }
        Map<String, Object> refreshment = location?.properties?.refreshment ? location.properties.refreshment.properties.subMap(['food', 'drink', 'quantity']).findAll { it.value } : null

        respond location, model: [
            generalData: generalData,
            mounting: mounting,
            requirements: requirements,
            refreshment: refreshment,
            colorList: TableLinen.findAllByLocation(location)
        ]
    }

    def showAll(final Long locationId) {
        Location location = locationService.find(locationId)

        if (!location) response.sendError 404

        respond location, model: [colorList: TableLinen.findAllByLocation(location)]
    }

    def edit(final Long id) {
        Location location = locationService.find(id)

        if (!location) response.sendError 404

        respond location, model: [
            hourList: Helper.HOURS,
            montageList: Helper.MONTAGE,
            colorList: colorService.findAll(),
            classroomList: classroomService.groupedByCode(),
            endTime: Helper.getTime(location.endDateAndTime),
            startTime: Helper.getTime(location.startDateAndTime),
            organizerColorList: TableLinen.findAllByLocation(location)
        ]
    }

    def update(UpdateLocationCommand command) {
        if (command.hasErrors()) {
            Location location = locationService.find(command.id)

            respond ([errors: command.errors], model: [
                location: location,
                hourList: Helper.HOURS,
                montageList: Helper.MONTAGE,
                colorList: colorService.findAll(),
                classroomList: classroomService.groupedByCode(),
                endTime: Helper.getTime(location.endDateAndTime),
                startTime: Helper.getTime(location.startDateAndTime),
                organizerColorList: TableLinen.findAllByLocation(location)
            ], view: 'edit')

            return
        }

        try {
            Location location = locationService.update(
                command.id,
                command.activityId,
                command.place,
                Helper.setTimeToDate(command.startDateAndTime, command.interval.first()),
                Helper.setTimeToDate(command.startDateAndTime, command.interval.last()),
                command.participants,
                command.typeOfAssembly,
                command.colors,
                command.datashow,
                command.podium,
                command.displayTable,
                command.flags,
                command.water,
                command.coffee,
                command.nationalAnthem,
                command.universityAnthem,
                command.triumphalAnthem,
                command.sound,
                command.projectorTable,
                command.waterBottles,
                command.observation
            )

            flash.message = 'Ubicacion actualizada'
            redirect uri: "/employees/${command.employeeId}/activities/${command.activityId}/locations/${location.id}", method: 'GET'
        } catch(ValidationException e) {
            Location location = locationService.find(command.id)

            respond ([errors: e.errors], model: [
                location: location,
                hourList: Helper.HOURS,
                montageList: Helper.MONTAGE,
                colorList: colorService.findAll(),
                classroomList: classroomService.groupedByCode(),
                endTime: Helper.getTime(location.endDateAndTime),
                startTime: Helper.getTime(location.startDateAndTime),
                organizerColorList: TableLinen.findAllByLocation(location)
            ], view: 'edit')
        }
    }

    def delete(final Long activityId, final Long employeeId, final Long id) {
        Location location = locationService.delete(id)

        if (!location) {
            notFound(employeeId, activityId, id)

            return
        }

        flash.message = 'Ubicacion eliminada'
        redirect uri: "/employees/$employeeId/activities/$activityId/locations", method: 'GET'
    }

    def clone(final Long locationId) {
        Location location = locationService.find(locationId)
        Location clonedLocation = location.clone()

        respond clonedLocation, model: [
            hourList: Helper.HOURS,
            montageList: Helper.MONTAGE,
            colorList: colorService.findAll(),
            classroomList: classroomService.groupedByCode(),
            endTime: Helper.getTime(location.endDateAndTime),
            startTime: Helper.getTime(location.startDateAndTime),
            organizerColorList: TableLinen.findAllByLocation(location)
        ], view: 'create'
    }

    private void notFound(final Long employeeId, final Long activityId, final Long id) {
        flash.message = "La ubicacion con id: $id no se ha encontrado"
        redirect uri: "/employees/$employeeId/activities/$activityId/locations", method: 'GET'
    }
}
