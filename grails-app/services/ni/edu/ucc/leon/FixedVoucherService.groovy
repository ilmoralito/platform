package ni.edu.ucc.leon

import org.hibernate.transform.AliasToEntityMapResultTransformer
import org.hibernate.SessionFactory
import grails.gorm.services.Service
import grails.gorm.services.Where

import ni.edu.ucc.leon.voucher.UpdateFixedVoucher

interface IFixedVoucherService {

    FixedVoucher find(final Serializable id)

    FixedVoucher delete(final Serializable id)

    FixedVoucher update(final UpdateFixedVoucher command)

    List<FixedVoucher> filter(final Date sinceDate, final Date tillDate)

    List<Number> getYearList()

    List<Map> getReport()

    @Where({ month(date) == month && year(date) == year })
    List<FixedVoucher> getReportSummary(final Integer month, final Integer year)

    @Where({ month(date) == month })
    List<FixedVoucher> getReportSummary(final Integer month)
}

@Service(FixedVoucher)
abstract class FixedVoucherService implements IFixedVoucherService {

    @Autowired
    org.hibernate.SessionFactory sessionFactory

    @Override
    FixedVoucher update(final UpdateFixedVoucher command) {
        FixedVoucher fixedVoucher = find(command.id)

        if (fixedVoucher) {
            fixedVoucher.with {
                date = command.date
                coffeeShop = command.coffeeShop
                employee = command.employee
                coordination = command.coordination
                price = command.price
                breakfast = command.breakfast
                lunch = command.lunch
                dinner = command.dinner
                others = command.others

                save(flush: true, failOnError: true)
            }
        }

        fixedVoucher
    }

    @Override
    List<FixedVoucher> filter(final Date sinceDate, final Date tillDate) {
        FixedVoucher.where { date >= sinceDate && date <= tillDate }.list()
    }

    @Override
    List<Number> getYearList() {
        FixedVoucher.executeQuery('SELECT DISTINCT(YEAR(fv.date)) AS year FROM FixedVoucher fv ORDER BY year DESC')
    }

    @Override
    List<Map> getReport() {
        final session = sessionFactory.currentSession
        final String queryString = """
            SELECT
                s.month month,
                s.m pivot,
                COUNT(v.id) total
            FROM (
                  SELECT  1 m, 'Jan' AS month
                  UNION SELECT 2, 'Feb'
                  UNION SELECT 3, 'Mar'
                  UNION SELECT 4, 'Apr'
                  UNION SELECT 5, 'May'
                  UNION SELECT 6, 'Jun'
                  UNION SELECT 7, 'Jul'
                  UNION SELECT 8, 'Aug'
                  UNION SELECT 9, 'Sep'
                  UNION SELECT 10, 'Oct'
                  UNION SELECT 11, 'Nov'
                  UNION SELECT 12, 'Dec'
            ) s
            LEFT JOIN (vouchers v) ON (s.m = MONTH(v.date)) AND v.class = 'ni.edu.ucc.leon.FixedVoucher'
            GROUP BY 1, 2 ORDER BY pivot DESC"""
        final sqlQuery = session.createSQLQuery(queryString)
        final result = sqlQuery.with {
            resultTransformer = AliasToEntityMapResultTransformer.INSTANCE

            list()
        }

        result
    }
}
