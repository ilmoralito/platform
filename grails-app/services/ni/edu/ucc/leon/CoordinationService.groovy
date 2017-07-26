package ni.edu.ucc.leon

import groovy.transform.CompileStatic
import grails.gorm.services.Service
import grails.gorm.services.Query

@CompileStatic
interface ICoordinationService {

    List<Coordination> list(Map args)

    Coordination find(Serializable id)

    Coordination save(String name, String acronym, String extensionNumber, Integer copyFee, String area)

    Coordination update(Serializable id, String name, String acronym, String extensionNumber, Integer copyFee, String area)

    Coordination delete(Serializable id)

    List<Coordination> listNotInList(List<String> nameList)
}

@Service(Coordination)
abstract class CoordinationService implements ICoordinationService {

    List<Coordination> listNotInList(List<String> nameList) {
        Coordination.executeQuery('''
            SELECT NEW MAP(
                c.id as id,
                c.name as name
            )
            FROM
                Coordination c
            WHERE
                c.name NOT IN (:nameList)
        ''', [nameList: nameList])
    }
}
