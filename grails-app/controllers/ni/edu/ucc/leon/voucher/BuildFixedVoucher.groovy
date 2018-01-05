package ni.edu.ucc.leon.voucher

import grails.databinding.BindingFormat

class BuildFixedVoucher implements grails.validation.Validateable {

    @BindingFormat('yyyy-MM-dd')
    Date date

    static constraints = {
        date nullable: false, validator: { date -> date >= new Date() }
    }
}