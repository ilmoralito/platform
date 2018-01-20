package ni.edu.ucc.leon

import org.hibernate.transform.AliasToEntityMapResultTransformer
import org.hibernate.SessionFactory

class PhoneBookService {

    @Autowired SessionFactory sessionFactory

    List<Map<String, Object>> list() {
        final session = sessionFactory.currentSession
        final String query = """
            SELECT
                c.extension_number extensionNumber,
                c.name coordination,
                (
                    SELECT e.full_name
                    FROM
                        employees e
                            LEFT JOIN
                        employee_coordinations ec ON e.id = ec.employee_id
                    WHERE
                        ec.position = 'manager'
                            AND e.enabled = true
                                AND ec.coordination_id = c.id
                ) manager,
                (
                    SELECT
                        GROUP_CONCAT(e.full_name SEPARATOR ', ')
                    FROM
                        employees e
                            LEFT JOIN
                        employee_coordinations ec ON e.id = ec.employee_id
                    WHERE
                        ec.position = 'assistant'
                            AND e.enabled = true
                                AND ec.coordination_id = c.id
                ) assistants
            FROM
                coordinations c
            where c.extension_number is not null
            ORDER BY c.extension_number"""

        final sqlQuery = session.createSQLQuery(query)
        final results = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            list()
        }

        results
    }

    List<Map<String, Object>> phoneBookSummary() {
        List<Map<String, Object>> phoneBook = list()
        List<String> extensionNumbers = phoneBook.extensionNumber.unique().sort()

        extensionNumbers.collect { extensionNumber ->
            [
                extensionNumber: extensionNumber,
                coordinations: phoneBook.findAll { it.extensionNumber == extensionNumber }.collect { coordination ->
                    [
                        name: coordination.coordination,
                        manager: coordination.manager,
                        assistants: coordination.assistants
                    ]
                }
            ]
        }
    }
}
