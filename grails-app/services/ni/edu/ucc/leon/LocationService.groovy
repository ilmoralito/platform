package ni.edu.ucc.leon

import ni.edu.ucc.leon.ClassroomService
import grails.gorm.services.Service

interface ILocationService {

    Location find(final Serializable id)

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
        final Boolean projectorTable,
        final Integer waterBottles,
        final String observation
    ) {
        Classroom classroom = classroomService.find(place)
        Activity activity = activityService.find(activityId)

        Location location = new Location (
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

        TableLinen.removeAll(location)

        location.delete()

        location
    }
}
