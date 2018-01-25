package ni.edu.ucc.leon.birthday

import ni.edu.ucc.leon.BirthdayService

class BirthdayController {

    BirthdayService birthdayService

    def index() {
        List<Map> birthday = birthdayService.birthdaysOfTheMonth().groupBy { it.day }.collect {
            [day: it.key, birthdayList: it.value.fullName]
        }

        respond ([], model: [birthday: birthday, birthdaysOfTheDay: birthdayService.birthdaysOfTheDay()])
    }
}
