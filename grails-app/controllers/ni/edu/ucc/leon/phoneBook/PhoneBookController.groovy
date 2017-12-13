package ni.edu.ucc.leon.phoneBook

import ni.edu.ucc.leon.PhoneBookService

class PhoneBookController {

    PhoneBookService phoneBookService

    def index() {
        respond ([phoneBook: phoneBookService.phoneBookSummary()])
    }
}
