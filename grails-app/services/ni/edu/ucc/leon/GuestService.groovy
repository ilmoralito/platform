package ni.edu.ucc.leon

import grails.gorm.services.Service

@Service(Guest)
interface GuestService {

    List<Guest> findAll()

    Guest save(final String fullName)

    Guest find(final Serializable id)

    Guest delete(final Serializable id)

    Guest update(final Serializable id, final String fullName)
}
