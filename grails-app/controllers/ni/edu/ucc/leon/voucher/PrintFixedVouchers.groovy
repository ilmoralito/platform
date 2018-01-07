package ni.edu.ucc.leon.voucher

class PrintFixedVouchers implements grails.validation.Validateable {

    List<Long> fixedVouchers

    static constraints = {
        fixedVouchers nullable: false, min: 1
    }
}