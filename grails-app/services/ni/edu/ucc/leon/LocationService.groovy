package ni.edu.ucc.leon

import ni.edu.ucc.leon.location.SaveLocationCommand
import ni.edu.ucc.leon.ClassroomService
import grails.gorm.services.Service
import grails.gorm.services.Query
import ni.edu.ucc.leon.Location
import ni.edu.ucc.leon.Helper

interface ILocationService {

    @Query("""
        SELECT DISTINCT
            NEW MAP((DATE_FORMAT(l.startDateAndTime, '%Y-%m-%d')) AS date)
        FROM
            ${Location l}
        WHERE
            l.activity.id = $activityId""")
    List<Date> locationDateList(final Serializable activityId)

    Location find(final Serializable id)

    List<Location> weeklyLogistics()

    List<Map> listWeeklyLogistics(final String type)

    List<Location> findAllByActivity(final Serializable activityId)

    Location save(
        final Serializable activityId,
        final Serializable place,
        final Date startDateAndTime,
        final Date endDateAndTime,
        final Integer participants,
        final String typeOfAssembly,
        final List<Long> colors,
        final Boolean datashow,
        final Boolean podium,
        final Boolean displayTable,
        final Boolean flags,
        final Boolean water,
        final Boolean coffee,
        final Boolean nationalAnthem,
        final Boolean universityAnthem,
        final Boolean triumphalAnthem,
        final Boolean sound,
        final Boolean microphone,
        final Boolean projectorTable,
        final Integer waterBottles,
        final String observation
    )

    Location update(
        final Serializable id,
        final Serializable activityId,
        final Serializable place,
        final Date startDateAndTime,
        final Date endDateAndTime,
        final Integer participants,
        final String typeOfAssembly,
        final List<Long> colors,
        final Boolean datashow,
        final Boolean podium,
        final Boolean displayTable,
        final Boolean flags,
        final Boolean water,
        final Boolean coffee,
        final Boolean nationalAnthem,
        final Boolean universityAnthem,
        final Boolean triumphalAnthem,
        final Boolean sound,
        final Boolean microphone,
        final Boolean projectorTable,
        final Integer waterBottles,
        final String observation
    )

    Location delete(final Serializable id)
}

@Service(Location)
abstract class LocationService implements ILocationService {

    @Autowired ColorService colorService
    @Autowired ActivityService activityService
    @Autowired ClassroomService classroomService

    @Override
    List<Location> weeklyLogistics() {
        Location.where {
            activity.state == 'authorized' &&
            startDateAndTime >= Helper.FIRST_DAY_OF_WEEK() &&
            startDateAndTime <= Helper.LAST_DAY_OF_WEEK()
        }.list()
    }

    @Override
    List<Map> listWeeklyLogistics(final String type) {
        List<Location> locationList = weeklyLogistics()

        Closure results = {
            if (type == 'concierge') {
                return groupByStartDate(locationList).collect {
                    [
                        date: it.key,
                        locations: it.value.collect { location ->
                            [
                                name: location.activity.name,
                                place: location.place.code,
                                startTime: location.startDateAndTime.format('hh:mm'),
                                endTime: location.endDateAndTime.format('hh:mm'),
                                participants: location.participants,
                                typeOfAssembly: location.typeOfAssembly,
                                requirements: getRequirements([
                                    [label: 'Podio', requirement: location.podium],
                                    [label: 'Mesa para expositor', requirement: location.displayTable],
                                    [label: 'Mesa para datashow', requirement: location.projectorTable]
                                ])
                            ]
                        }
                    ]
                }
            } else if (type == 'generalServices') {
                return groupByStartDate(locationList).collect {
                    [
                        date: it.key,
                        locations: it.value.collect { location ->
                            [
                                name: location.activity.name,
                                place: location.place.code,
                                startTime: location.startDateAndTime.format('hh:mm'),
                                endTime: location.endDateAndTime.format('hh:mm'),
                                participants: location.participants,
                                requirements: getRequirements([
                                    [label: 'Datashow', requirement: location.datashow],
                                    [label: 'Sonido', requirement: location.sound],
                                    [label: 'Himno nacional', requirement: location.nationalAnthem],
                                    [label: 'Himno de la universidad', requirement: location.universityAnthem],
                                    [label: 'Marcha triunfal', requirement: location.triumphalAnthem],
                                    [label: 'Microfono', requirement: location.microphone]
                                ])
                            ]
                        }
                    ]
                }
            } else if (type == 'protocol') {
                return groupByStartDate(locationList).collect {
                    [
                        date: it.key,
                        locations: it.value.collect { location ->
                            [
                                name: location.activity.name,
                                place: location.place.code,
                                startTime: location.startDateAndTime.format('hh:mm'),
                                endTime: location.endDateAndTime.format('hh:mm'),
                                participants: location.participants,
                                tableLinen: TableLinen.findAllByLocation(location).name,
                                waterBottles: location.waterBottles,
                                quantity: location?.refreshment?.quantity,
                                requirements: getRequirements([
                                    [label: 'Agua', requirement: location.water],
                                    [label: 'Cafe', requirement: location.coffee],
                                    [label: 'Comida', requirement: location?.refreshment?.food],
                                    [label: 'Bebida', requirement: location?.refreshment?.drink]
                                ])
                            ]
                        }
                    ]
                }
            }
        }

        results()
    }

    @Override
    List<Location> findAllByActivity(final Serializable activityId) {
        Activity activity = activityService.find(activityId)

        Location.where { activity == activity }.list()
    }

    @Override
    Location save(
        final Serializable activityId,
        final Serializable place,
        final Date startDateAndTime,
        final Date endDateAndTime,
        final Integer participants,
        final String typeOfAssembly,
        final List<Long> colors,
        final Boolean datashow,
        final Boolean podium,
        final Boolean displayTable,
        final Boolean flags,
        final Boolean water,
        final Boolean coffee,
        final Boolean nationalAnthem,
        final Boolean universityAnthem,
        final Boolean triumphalAnthem,
        final Boolean sound,
        final Boolean microphone,
        final Boolean projectorTable,
        final Integer waterBottles,
        final String observation
    ) {
        Activity activity = activityService.find(activityId)
        Classroom classroom = classroomService.find(place)

        Location location = new Location(
            place: classroom,
            startDateAndTime: startDateAndTime,
            endDateAndTime: endDateAndTime,
            participants: participants,
            typeOfAssembly: typeOfAssembly,
            datashow: datashow,
            podium: podium,
            displayTable: displayTable,
            flags: flags,
            water: water,
            coffee: coffee,
            nationalAnthem: nationalAnthem,
            universityAnthem: universityAnthem,
            triumphalAnthem: triumphalAnthem,
            sound: sound,
            microphone: microphone,
            projectorTable: projectorTable,
            waterBottles: waterBottles,
            observation: observation
        )

        activity.addToLocations(location)

        location.save(failOnError: true)

        for(Long color in colors) {
            Color colorInstane = colorService.find(color)

            TableLinen.create(colorInstane, location)
        }

        location
    }

    @Override
    Location update(
        final Serializable id,
        final Serializable activityId,
        final Serializable place,
        final Date startDateAndTime,
        final Date endDateAndTime,
        final Integer participants,
        final String typeOfAssembly,
        final List<Long> colors,
        final Boolean datashow,
        final Boolean podium,
        final Boolean displayTable,
        final Boolean flags,
        final Boolean water,
        final Boolean coffee,
        final Boolean nationalAnthem,
        final Boolean universityAnthem,
        final Boolean triumphalAnthem,
        final Boolean sound,
        final Boolean microphone,
        final Boolean projectorTable,
        final Integer waterBottles,
        final String observation
    ) {
        Classroom classroom = classroomService.find(place)
        Location location = find(id)

        if (location) {
            location.place = classroom
            location.startDateAndTime = startDateAndTime
            location.endDateAndTime = endDateAndTime
            location.participants = participants
            location.typeOfAssembly = typeOfAssembly
            location.datashow = datashow
            location.podium = podium
            location.displayTable = displayTable
            location.flags = flags
            location.water = water
            location.coffee = coffee
            location.nationalAnthem = nationalAnthem
            location.universityAnthem = universityAnthem
            location.triumphalAnthem = triumphalAnthem
            location.sound = sound
            location.microphone = microphone
            location.projectorTable = projectorTable
            location.waterBottles = waterBottles
            location.observation = observation

            location.save(failOnError: true)

            TableLinen.removeAll(location)

            for(Long color in colors) {
                Color colorInstane = colorService.find(color)

                TableLinen.create(colorInstane, location)
            }
        }

        location
    }

    @Override
    Location delete(final Serializable id) {
        Location location = find(id)

        // Remove table linen
        TableLinen.removeAll(location)

        // Remove vouchers
        Number employeeResult = EmployeeVoucher.where {
            activity == location.activity && date == location.startDateAndTime.clearTime()
        }.deleteAll() as int

        log.info "$employeeResult employee vouchers affected"

        Number guestResult = GuestVoucher.where {
            activity == location.activity && date == location.startDateAndTime.clearTime()
        }.deleteAll() as int

        log.info "$guestResult guest vouchers affected"

        // Remove location instance
        location.delete()

        location
    }

    private final Map groupByStartDate(final List locationList) {
        locationList.groupBy { location -> location.startDateAndTime.format('yyyy-MM-dd') }
    }

    private final List<String> getRequirements(final List<Map> properties) {
        List<String> requirements = properties.inject([]) { accumulator, currentValue ->
            if (currentValue.requirement) {
                accumulator << currentValue.label
            }

            accumulator
        }

        requirements
    }
}
