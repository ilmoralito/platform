package ni.edu.ucc.leon.birthday

import ni.edu.ucc.leon.BirthdayService

class BirthdayController {

    @Autowired BirthdayService birthdayService

    def index() {
        [birthday: birthdayService.listBirthdayInMonth(), birthdayOfTheToday: birthdayService.listBirthdayOfTheDay()]
    }
}
