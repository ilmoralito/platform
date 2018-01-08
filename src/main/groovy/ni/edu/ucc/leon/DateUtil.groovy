package ni.edu.ucc.leon

import groovy.transform.TypeCheckingMode
import groovy.transform.CompileStatic
import static java.util.Calendar.*

@CompileStatic
class DateUtil {

    @CompileStatic(TypeCheckingMode.SKIP)
    public Date getFirstDayOfTheWeek() {
        Calendar calendar = Calendar.instance

        calendar.set(DAY_OF_WEEK, calendar.getActualMinimum(DAY_OF_WEEK))

        calendar.getTime()
    }

    public Date getLastDayOfTheWeek() {
        getFirstDayOfTheWeek() + 6
    }
}
