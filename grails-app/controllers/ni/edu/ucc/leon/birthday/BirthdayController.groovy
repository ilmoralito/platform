package ni.edu.ucc.leon.birthday

import ni.edu.ucc.leon.BirthdayService

class BirthdayController {

    BirthdayService birthdayService

    def index() {
        List<Map> birthday = birthdayService.listBirthdayInMonth().groupBy { it.day }.collect {
            [day: it.key, birthdayList: it.value.fullName]
        }

        [
            birthday: birthday,
            birthdayOfTheToday: birthdayService.listBirthdayOfTheDay()
        ]
    }
}
