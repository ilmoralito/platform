package ni.edu.ucc.leon

import groovy.transform.CompileStatic
import grails.gorm.services.Service

@Service(Device)
@CompileStatic
interface DeviceService {

    Device find(Serializable id)

    List<Device> list()

    Device save(String name)

    Device update(Serializable id, String name)

    Device delete(Serializable id)
}
