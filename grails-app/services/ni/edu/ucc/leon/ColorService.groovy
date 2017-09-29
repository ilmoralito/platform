package ni.edu.ucc.leon

import grails.gorm.services.Service

@Service(Color)
interface ColorService {

    Color find(final Serializable id)

    List<Color> findAll()

    Color save(final String name)

    Color update(final Serializable id, final String name)

    Color delete(final Serializable id)
}
