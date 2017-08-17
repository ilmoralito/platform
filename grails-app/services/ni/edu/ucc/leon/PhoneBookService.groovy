package ni.edu.ucc.leon

import org.hibernate.transform.AliasToEntityMapResultTransformer
import org.hibernate.SessionFactory

class PhoneBookService {

    @Autowired SessionFactory sessionFactory

    List<Map> listPhoneBook() {
        final session = sessionFactory.currentSession

        final String query = """
            SELECT 
                c.extension_number extensionNumber,
                c.name coordination,
                (SELECT 
                        e.full_name
                    FROM
                        employees e
                            LEFT JOIN
                        employee_coordinations ec ON e.id = ec.employee_id
                    WHERE
                        ec.position = 'manager'
                            AND ec.coordination_id = c.id) manager,
                (SELECT 
                        GROUP_CONCAT(e.full_name SEPARATOR ', ')
                    FROM
                        employees e
                            LEFT JOIN
                        employee_coordinations ec ON e.id = ec.employee_id
                    WHERE
                        ec.position = 'assistant'
                            AND ec.coordination_id = c.id) assistants
            FROM
                coordinations c
            ORDER BY c.extension_number;
        """

        final sqlQuery = session.createSQLQuery(query)

        final results = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE
            list()
        }

        results
    }
}
