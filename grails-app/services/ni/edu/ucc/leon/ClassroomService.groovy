package ni.edu.ucc.leon

import grails.gorm.services.Service

interface IClassroomService {

    List<Classroom> list(Map args)

    Classroom find(Serializable id)

    Classroom save(String code, String name, Integer capacity, Boolean wifi, Boolean airConditioned, Integer powerOutletNumber)

    Classroom update(Serializable id, String code, String name, Integer capacity, Boolean wifi, Boolean airConditioned, Integer powerOutletNumber)

    Classroom delete(Serializable id)

    List<Map> groupedByCode()
}

@Service(Classroom)
abstract class ClassroomService implements IClassroomService {

    List<Map> groupedByCode() {
        List<Classroom> classroomList = list()
        List<String> keys = ['id', 'code', 'name', 'capacity', 'wifi', 'airConditioned', 'powerOutletNumber']

        List<Map> result = classroomList.groupBy { classroom -> classroom.code[0] }.collect { item ->
            [ code: item.key, classrooms: item.value*.properties*.subMap(keys) ]
        }

        result
    }
}
