package ni.edu.ucc.leon

import grails.gorm.services.Service
import ni.edu.ucc.leon.voucher.UpdateFixedVoucher

interface IFixedVoucherService {

    List<FixedVoucher> findAll()

    FixedVoucher find(final Serializable id)

    FixedVoucher delete(final Serializable id)

    FixedVoucher update(final UpdateFixedVoucher command)

    List<FixedVoucher> filter(final Date sinceDate, final Date tillDate)
}

@Service(FixedVoucher)
abstract class FixedVoucherService implements IFixedVoucherService {

    @Override
    FixedVoucher update(final UpdateFixedVoucher command) {
        FixedVoucher fixedVoucher = find(command.id)

        if (fixedVoucher) {
            fixedVoucher.with {
                date = command.date
                coffeeShop = command.coffeeShop
                employee = command.employee
                coordination = command.coordination
                price = command.price
                breakfast = command.breakfast
                lunch = command.lunch
                dinner = command.dinner
                others = command.others

                save(flush: true, failOnError: true)
            }
        }

        fixedVoucher
    }

    @Override
    List<FixedVoucher> filter(final Date sinceDate, final Date tillDate) {
        FixedVoucher.where { date >= sinceDate && date <= tillDate }.list()
    }
}
