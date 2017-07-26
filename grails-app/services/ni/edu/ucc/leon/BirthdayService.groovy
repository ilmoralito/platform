package ni.edu.ucc.leon

import org.hibernate.transform.AliasToEntityMapResultTransformer
import org.hibernate.SessionFactory
import static java.util.Calendar.*

class BirthdayService {

    @Autowired SessionFactory sessionFactory

    List<Map<String, Object>> listBirthdayInMonth() {
        final session = sessionFactory.currentSession
        final Date today = new Date()
        final Integer month = today[Calendar.MONTH] + 1
        final String query = """
            SELECT
                e.full_name fullName,
                DAY(STR_TO_DATE(SUBSTR(e.identity_card, 5, 6), '%d%m%Y')) day
            FROM
                employees e
            WHERE
                MONTH(STR_TO_DATE(SUBSTR(e.identity_card, 5, 6), '%d%m%Y')) = :month
            ORDER BY 2 DESC
        """
        final sqlQuery = session.createSQLQuery(query)
        final results = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            setLong('month', month)

            list()
        }

        results
    }

    List<Map<String, Object>> listBirthdayOfTheDay() {
        final session = sessionFactory.currentSession
        final Date today = new Date()
        final Integer month = today[Calendar.MONTH] + 1
        final Integer dayOfMonth = today[Calendar.DAY_OF_MONTH]
        final String query = """
            SELECT 
                e.full_name fullName
            FROM
                employees e
            WHERE
                DAY(STR_TO_DATE(SUBSTR(e.identity_card, 5, 6), '%d%m%Y')) = :dayOfMonth
                    AND MONTH(STR_TO_DATE(SUBSTR(e.identity_card, 5, 6), '%d%m%Y')) = :month
        """
        final sqlQuery = session.createSQLQuery(query)
        final results = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            setLong('dayOfMonth', dayOfMonth)
            setLong('month', month)

            list()
        }

        results
    }

    List<Map<String, Object>> getBirthdayOfTheDay() {
        final session = sessionFactory.currentSession
        final Date today = new Date()
        final Integer month = today[Calendar.MONTH] + 1
        final Integer dayOfMonth = today[Calendar.DAY_OF_MONTH]
        final String query = """
            SELECT 
                e.full_name fullName, u.email email
            FROM
                employees e
                    INNER JOIN
                users u ON e.user_id = u.id
            WHERE
                DAY(STR_TO_DATE(SUBSTR(e.identity_card, 5, 6), '%d%m%Y')) = :dayOfMonth
                    AND MONTH(STR_TO_DATE(SUBSTR(e.identity_card, 5, 6), '%d%m%Y')) = :month
        """
        final sqlQuery = session.createSQLQuery(query)
        final results = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            setLong('dayOfMonth', dayOfMonth)
            setLong('month', month)

            list()
        }

        results
    }
}
