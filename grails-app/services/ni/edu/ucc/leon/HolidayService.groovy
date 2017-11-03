package ni.edu.ucc.leon

import groovy.transform.CompileStatic
import grails.gorm.services.Service
import static java.util.Calendar.*
import ni.edu.ucc.leon.Helper

interface IHolidayService {

    List<Holiday> list()

    Holiday find(final Serializable id)

    Holiday save(final Date date, final String name, final String wiki)

    Holiday update(final Serializable id, final Date date, final String name, final String wiki)

    Holiday delete(final Serializable id)

    List<Map<String, String>> listInCurrentMonth()

    List<Map<String, Object>> listOfHolidaysGroupedByMonth()
}

@Service(Holiday)
// @CompileStatic
abstract class HolidayService implements IHolidayService {

    List<Map<String, String>> listInCurrentMonth() {
        Holiday.executeQuery('''
            SELECT
                new map (DAYOFMONTH(h.date) AS dayOfMonth, h.name AS name, h.wiki AS wiki)
            FROM
                Holiday h
            WHERE
                MONTH(h.date) = :month
        ''', [month: new Date()[Calendar.MONTH] + 1])
    }

    List<Map<String, Object>> listOfHolidaysGroupedByMonth() {
        List<Holiday> holidayList = list()
        List<Integer> monthList = holidayList.collect { it.date[Calendar.MONTH] }.sort().unique()

        List<Map<String, Object>> results = monthList.collect { month ->
            [
                month: Helper.MONTHS[month],
                holidays: holidayList.findAll { holiday ->
                    holiday.date[Calendar.MONTH] == month
                }.collect {
                    [
                        dayOfMonth: it.date[Calendar.DAY_OF_MONTH],
                        name: it.name,
                        wiki: it.wiki
                    ]
                }
            ]
        }

        results
    }
}
