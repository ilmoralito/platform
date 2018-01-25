package ni.edu.ucc.leon

import org.hibernate.transform.AliasToEntityMapResultTransformer
import org.hibernate.SessionFactory
import static java.util.Calendar.*
import ni.edu.ucc.leon.DateUtil

class BirthdayService {

    @Autowired SessionFactory sessionFactory
    DateUtil dateUtil

    List<Map> birthdaysOfTheMonth() {
        final session = sessionFactory.currentSession
        final String query = """
            SELECT
                e.full_name fullName,
                DAY(STR_TO_DATE(SUBSTR(e.identity_card, 5, 6), '%d%m%Y')) day
            FROM
                employees e
            WHERE
                MONTH(STR_TO_DATE(SUBSTR(e.identity_card, 5, 6), '%d%m%Y')) = :month
                    AND e.enabled = true
            ORDER BY 2 DESC"""
        final sqlQuery = session.createSQLQuery(query)
        final results = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            setInteger('month', dateUtil.getCurrentMonth())

            list()
        }

        results
    }

    List<Map> birthdaysOfTheDay() {
        final session = sessionFactory.currentSession
        final String query = """
            SELECT
                e.full_name fullName,
                u.email email
            FROM
                employees e INNER JOIN users u on e.user_id = u.id
            WHERE
                DAY(STR_TO_DATE(SUBSTR(e.identity_card, 5, 6), '%d%m%Y')) = :dayOfMonth
                    AND MONTH(STR_TO_DATE(SUBSTR(e.identity_card, 5, 6), '%d%m%Y')) = :month
                        AND e.enabled = true"""
        final sqlQuery = session.createSQLQuery(query)
        final results = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            setInteger('dayOfMonth', dateUtil.getCurrentDay())
            setInteger('month', dateUtil.getCurrentMonth())

            list()
        }

        results
    }
}
