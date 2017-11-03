package ni.edu.ucc.leon

import static java.util.Calendar.*

class ConstraintsUtils {

    public static final Closure validateStartDateAndTime() {
        { startDateAndTime ->
            isValidApplicationDate(startDateAndTime) && isValidStartTime(getHour(startDateAndTime))
        }
    }

    public static final Closure validateEndDateAndTime() {
        { endDateAndTime, object ->
            isValidEndDate(object.startDateAndTime, endDateAndTime) && isValidEndTime(getHour(object.startDateAndTime), getHour(endDateAndTime))
        }
    }

    private static final Integer getHour(final Date datetime) {
        datetime.toCalendar().get(HOUR_OF_DAY)
    }

    private static final Boolean isValidApplicationDate(final Date startDateAndTime) {
        List<Date> dates = (new Date()..startDateAndTime)

        dates.size() >= 3
    }

    private static final Boolean isValidEndDate(final Date startDateAndTime, final Date endDateAndTime) {
        Date date1 = startDateAndTime.clone()
        Date date2 = endDateAndTime.clone()

        date1.clearTime() == date2.clearTime()
    }

    private static final Boolean isValidStartTime(final Integer startTime) {
        startTime >= 8 && startTime <= 16
    }

    private static final Boolean isValidEndTime(final Integer startTime, final Integer endTime) {
        endTime > startTime && endTime <= 17
    }
}