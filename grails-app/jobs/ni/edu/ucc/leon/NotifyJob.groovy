package ni.edu.ucc.leon

import grails.plugins.mail.MailService

class NotifyJob {

    @Autowired BirthdayService birthdayService
    @Autowired MailService mailService

    static triggers = {
        // simple name: 'mySimpleTrigger', startDelay: 60000, repeatInterval: 60000

        // cron name: 'greeting', cronExpression: '0 10 9 * * ?'
    }

    def execute() {
        log.info 'Sending mail'
        mailService.sendMail {
            from 'amakenadog@gmail.com'
            to 'mario.martinez@ucc.edu.ni'
            subject 'Hello world'
            html 'Hello world perfect blue milleneal acrties'
        }
        log.info 'Sent mail'

        // List<Map<String, Object>> birthdayParty = birthdayService.getBirthdayOfTheDay()

        // if (birthdayParty) {
        //     birthdayParty.each { birthday ->
        //         mailService.sendMail {
        //             from 'mario.martinez@ucc.edu.ni'
        //             to birthday.email
        //             subject 'Felicidades en tu cumpleaños de parte de UCC campus León'
        //             html "<p>Hola ${birthday.fullName}</p> <p>De parte de la Universidad de Ciencias Comerciales queremos desearte muchas feliciades en tu cumpleaños.</p> <p>Saludos y bendiciones para usted y tu familia</p>"
        //         }
        //     }
        // }
    }
}
