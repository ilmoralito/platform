package ni.edu.ucc.leon.voucher

import grails.databinding.BindingFormat

class ApplyFilter implements grails.validation.Validateable {

    @BindingFormat('yyyy-MM-dd')
    Date sinceDate

    @BindingFormat('yyyy-MM-dd')
    Date tillDate

    static constraints = {
        sinceDate nullable: false
        tillDate nullable: false
    }
}
