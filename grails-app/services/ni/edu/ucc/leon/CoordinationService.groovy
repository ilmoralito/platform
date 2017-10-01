package ni.edu.ucc.leon

import groovy.transform.CompileStatic
import grails.gorm.services.Service
import ni.edu.ucc.leon.ColorService
import grails.gorm.services.Query

@CompileStatic
interface ICoordinationService {

    List<Coordination> list(final Map args)

    Coordination find(final Serializable id)

    Coordination save(final String name, final String acronym, final String extensionNumber, final Integer copyFee, final String area, final ArrayList colors)

    Coordination update(final Serializable id, final String name, final String acronym, final String extensionNumber, final Integer copyFee, final String area, final ArrayList colors)

    Coordination delete(final Serializable id)

    List<Coordination> listNotInList(final List<String> nameList)
}

@Service(Coordination)
abstract class CoordinationService implements ICoordinationService {

    @Autowired ColorService colorService

    @Override
    List<Coordination> listNotInList(final List<String> nameList) {
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

    @Override
    Coordination save(final String name, final String acronym, final String extensionNumber, final Integer copyFee, final String area, final ArrayList colors) {
        Coordination coordination = new Coordination(
            name: name,
            acronym: acronym,
            extensionNumber: extensionNumber,
            copyFee: copyFee,
            area: area
        ).save(flush: true)

        if (colors) addColors(colors, coordination)

        coordination
    }

    @Override
    Coordination update(final Serializable id, final String name, final String acronym, final String extensionNumber, final Integer copyFee, final String area, final ArrayList colors) {
        Coordination coordination = find(id)

        if (coordination) {
            coordination.name = name
            coordination.acronym = acronym
            coordination.extensionNumber = extensionNumber
            coordination.copyFee = copyFee
            coordination.area = area

            CoordinationColor.removeAll(coordination)

            if (colors) addColors(colors, coordination)

            coordination.save(flush: true)
        }

        coordination
    }

    private void addColors(final List<Long> colors, final Coordination coordination) {
        for(color in colors) {
            Color colorInstance = colorService.find(color)

            if (colorInstance) CoordinationColor.create(colorInstance, coordination)
        }
    }
}
