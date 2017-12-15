package ni.edu.ucc.leon

import grails.gorm.services.Service

@Service(CoffeeShop)
interface CoffeeShopService {

    List<CoffeeShop> findAll()

    CoffeeShop find(final Serializable id)

    CoffeeShop delete(final Serializable id)

    CoffeeShop save(final String name, final Integer breakfast, final Integer lunch, final Integer dinner, final Integer others)

    CoffeeShop update(final Serializable id, final String name, final Integer breakfast, final Integer lunch, final Integer dinner, final Integer others)
}
