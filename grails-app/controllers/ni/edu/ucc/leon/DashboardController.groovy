package ni.edu.ucc.leon

import ni.edu.ucc.leon.PhoneBookService
import groovy.transform.CompileStatic
import ni.edu.ucc.leon.HolidayService
import ni.edu.ucc.leon.TicketService

@CompileStatic
class DashboardController {

    PhoneBookService phoneBookService
    HolidayService holidayService
    TicketService ticketService

    def index() {
        [
            phoneBook: phoneBookService.phoneBookSummary(),
            summaryStatus: ticketService.summaryStatus(),
            holidaysInMonth: holidayService.listInCurrentMonth(),
            holidayList: holidayService.listOfHolidaysGroupedByMonth(),
        ]
    }
}
