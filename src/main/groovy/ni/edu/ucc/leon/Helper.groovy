package ni.edu.ucc.leon

import groovy.transform.TypeCheckingMode
import groovy.transform.CompileStatic
import static java.util.Calendar.*
import java.text.SimpleDateFormat

@CompileStatic
class Helper {

    public static final List<String> TITLES = [
        'Licenciado',
        'Ingeniero',
        'Doctor',
        'Master'
    ]

    public static final List<LinkedHashMap> LOGISTICS_TYPE_LIST = [
        [english: 'concierge', spanish: 'Conserjeria'],
        [english: 'generalServices', spanish: 'Servicios generales'],
        [english: 'protocol', spanish: 'Protocolo'],
    ]

    public static final Date FIRST_DAY_OF_WEEK() {
        Calendar calendar = Calendar.instance
        Integer diff = Calendar.MONDAY - calendar.get(DAY_OF_WEEK)

        calendar.add DAY_OF_WEEK, diff

        calendar.getTime().clearTime()
    }

    public static final Date LAST_DAY_OF_WEEK() {
        FIRST_DAY_OF_WEEK() + 6
    }

    public static final List<String> ACTIVITY_STATE_LIST = [
        'created',
        'notified',
        'confirmed',
        'approved',
        'authorized',
        'attended',
        'canceled'
    ]

    public static final List<String> MONTHS = [
        'Enero',
        'Febrero',
        'Marzo',
        'Abril',
        'Mayo',
        'Junio',
        'Julio',
        'Agosto',
        'Septiembre',
        'Octubre',
        'Noviembre',
        'Diciembre'
    ]

    public static final List<LinkedHashMap> STATE_LIST = [
        [english: 'open', spanish: 'Abiertos'],
        [english: 'pending', spanish: 'En progreso'],
        [english: 'closed', spanish: 'Cerrados']
    ]

    public static final List<LinkedHashMap> HOURS = [
        ['twelveHourFormat': '8:00', 'twentyFourHourFormat': '08:00'],
        ['twelveHourFormat': '9:00', 'twentyFourHourFormat': '09:00'],
        ['twelveHourFormat': '10:00', 'twentyFourHourFormat': '10:00'],
        ['twelveHourFormat': '11:00', 'twentyFourHourFormat': '11:00'],
        ['twelveHourFormat': '12:00', 'twentyFourHourFormat': '12:00'],
        ['twelveHourFormat': '1:00', 'twentyFourHourFormat': '13:00'],
        ['twelveHourFormat': '2:00', 'twentyFourHourFormat': '14:00'],
        ['twelveHourFormat': '3:00', 'twentyFourHourFormat': '15:00'],
        ['twelveHourFormat': '4:00', 'twentyFourHourFormat': '16:00'],
        ['twelveHourFormat': '5:00', 'twentyFourHourFormat': '17:00']
    ]

    public static final List<LinkedHashMap> LOCATION_PROPERTIES = [
        [english: 'place', spanish: 'Lugar'],
        [english: 'startDateAndTime', spanish: 'Fecha y hora de inicio'],
        [english: 'endDateAndTime', spanish: 'Hora de clausura'],
        [english: 'participants', spanish: 'Participantes'],
        [english: 'typeOfAssembly', spanish: 'Tipo de montaje'],
        [english: 'podium', spanish: 'Podio'],
        [english: 'displayTable', spanish: 'Mesa para expositor'],
        [english: 'flags', spanish: 'Banderas'],
        [english: 'projectorTable', spanish: 'Mesa para proyector'],
        [english: 'datashow', spanish: 'Datashow'],
        [english: 'nationalAnthem', spanish: 'Himno nacional'],
        [english: 'universityAnthem', spanish: 'Himno de la universidad'],
        [english: 'triumphalAnthem', spanish: 'Marcha triunfal'],
        [english: 'sound', spanish: 'Sonido'],
        [english: 'water', spanish: 'Agua'],
        [english: 'coffee', spanish: 'Cafe'],
        [english: 'waterBottles', spanish: 'Numero de botellas de agua'],
        [english: 'food', spanish: 'Comida'],
        [english: 'drink', spanish: 'Bebida'],
        [english: 'quantity', spanish: 'Cantidad'],
        [english: 'observation', spanish: 'Observacion'],
    ]

    public static final List<String> MONTAGE = [
        'Auditorio con mesas',
        'Auditorio sin mesas',
        'En forma de U',
        'Libre'
    ]

    @CompileStatic(TypeCheckingMode.SKIP)
    public static final Date setTimeToDate(final Date date, final String time) {
        Calendar calendar = date.toCalendar()
        Integer hourOfDay = getHourOfDay(time)

        calendar.set hourOfDay: hourOfDay, minute: 0, second: 0

        calendar.getTime()
    }

    private static final Integer getHourOfDay(final String time) {
        time.tokenize(':')[0].toInteger()
    }

    private static final String getTime(final Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat('HH:mm')

        dateFormat.format(date)
    }
}