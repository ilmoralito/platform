package ni.edu.ucc.leon

import groovy.transform.CompileStatic
import grails.gorm.services.Service

@Service(Classroom)
@CompileStatic
interface ClassroomService {

    List<Classroom> list(Map args)

    Classroom find(Serializable id)

    Classroom save(String code, String name, Integer capacity, Boolean wifi, Boolean airConditioned)

    Classroom update(Serializable id, String code, String name, Integer capacity, Boolean wifi, Boolean airConditioned)

    Classroom delete(Serializable id)
}
