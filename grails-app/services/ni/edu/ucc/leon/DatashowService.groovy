package ni.edu.ucc.leon

import groovy.transform.CompileStatic
import grails.gorm.services.Service

@Service(Datashow)
@CompileStatic
interface DatashowService {

    List<Datashow> list(Map args)

    Datashow find(Serializable id)

    Datashow save(String trademark, String model, String serialNumber, Integer code, Boolean hdmi, Boolean wifi, Boolean ethernet)

    Datashow update(Serializable id, String trademark, String model, String serialNumber, Integer code, Boolean hdmi, Boolean wifi, Boolean ethernet, Boolean enabled)
}
