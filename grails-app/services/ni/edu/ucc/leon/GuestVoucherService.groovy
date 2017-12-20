package ni.edu.ucc.leon

import ni.edu.ucc.leon.voucher.SaveGuestVoucherCommand
import ni.edu.ucc.leon.ActivityService
import grails.gorm.services.Service

interface IGuestVoucherService {

    List<GuestVoucher> findAllByActivity(final Serializable activityId)

    List<String> getVoucherSummary(final Serializable activityId)

    List<Guest> getGuestList(final Serializable activityId, final java.sql.Timestamp date)

    Number save(SaveGuestVoucherCommand command)
}

@Service(GuestVoucher)
abstract class GuestVoucherService implements IGuestVoucherService {

    ActivityService activityService

    @Override
    List<GuestVoucher> findAllByActivity(final Serializable activityId) {
        Activity activity = activityService.find(activityId)

        GuestVoucher.where { activity == activity }.list()
    }

    @Override
    List<String> getVoucherSummary(final Serializable activityId) {
        List<GuestVoucher> guestVoucherList = findAllByActivity(activityId).sort { it.guest.fullName }
        List<Map> summary = guestVoucherList.groupBy { it.date }.collect {
            [
                date: it.key,
                vouchers: it.value.collect {
                    [
                        id: it.id,
                        toName: it.guest.fullName,
                        price: it.price,
                        services: [
                            [label: 'desayuno', service: it.breakfast],
                            [label: 'almuerzo', service: it.lunch],
                            [label: 'cena', service: it.dinner],
                            [label: 'otros', service: it.others]
                        ].inject([]) { accumulator, currentValue ->
                            if (currentValue.service) accumulator << currentValue.label

                            accumulator
                        }
                    ]
                }
            ]
        }.sort { it.date }

        summary
    }

    @Override
    List<Guest> getGuestList(final Serializable activityId, final java.sql.Timestamp date) {
        Activity activity = activityService.find(activityId)
        List<Guest> guestList = CoordinationGuest.guestList(activity.organizedBy)
        List<Guest> guestVoucherList = GuestVoucher.where { activity == activity && date == date }.list().guest

        guestList - guestVoucherList
    }

    @Override
    Number save(SaveGuestVoucherCommand command) {
        Activity activity = activityService.find(command.activityId)

        command.guests.each { guest ->
            GuestVoucher guestVoucher = new GuestVoucher(
                guest: guest,
                date: command.date,
                coffeeShop: command.coffeeShop,
                price: command.price,
                coordination: activity.organizedBy,
                breakfast: command.breakfast,
                lunch: command.lunch,
                dinner: command.dinner,
                others: command.others
            )

            activity.addToGuestVouchers(guestVoucher)

            guestVoucher.save(failOnError: true)
        }

        command.guests.size()
    }
}
