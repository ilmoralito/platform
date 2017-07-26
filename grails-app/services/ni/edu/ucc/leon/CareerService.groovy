package ni.edu.ucc.leon

import groovy.transform.CompileStatic
import grails.gorm.services.Service

@Service(Career)
@CompileStatic
interface CareerService {

    List<Career> list(Map args)

    Career find(Serializable id)

    Career save(String name)

    Career update(Serializable id, String name)

    Career delete(Serializable id)
}
