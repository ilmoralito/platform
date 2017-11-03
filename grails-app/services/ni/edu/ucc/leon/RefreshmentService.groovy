package ni.edu.ucc.leon

import grails.gorm.services.Service
import ni.edu.ucc.leon.LocationService

interface IRefreshmentService {

    Refreshment find(final Serializable id)

    Refreshment save(final String food, final String drink, final Integer quantity, final Serializable locationId)

    Refreshment update(final Serializable id, final String food, final String drink, final Integer quantity)

    void delete(final Serializable id, final Serializable locationId)
}

@Service(Refreshment)
abstract class RefreshmentService implements IRefreshmentService {

    @Autowired
    LocationService locationService

    @Override
    Refreshment save(final String food, final String drink, final Integer quantity, final Serializable locationId) {
        Location location = locationService.find(locationId)

        new Refreshment(
            food: food,
            drink: drink,
            quantity: quantity,
            location: location
        ).save(failOnError: true)
    }

    @Override
    void delete(final Serializable id, final Serializable locationId) {
        Location location = locationService.find(locationId)
        Refreshment refreshment = find(id)

        location.refreshment = null
        location.save()
        refreshment.delete()
    }
}
