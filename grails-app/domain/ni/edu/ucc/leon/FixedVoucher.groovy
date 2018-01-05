package ni.edu.ucc.leon

class FixedVoucher extends Voucher {

    static belongsTo = [employee: Employee]

    static constraints = {
    }
}
