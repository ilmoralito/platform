package ni.edu.ucc.leon

import grails.plugins.mail.MailService

class NotifyJob {

    BirthdayService birthdayService
    MailService mailService

    static triggers = {
        // simple name: 'mySimpleTrigger', startDelay: 60000, repeatInterval: 60000

        // cron name: 'greeting', cronExpression: '0 10 9 * * ?'
    }

    def execute() {
        // to implement
    }
}
