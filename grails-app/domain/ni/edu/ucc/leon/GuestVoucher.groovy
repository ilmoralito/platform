package ni.edu.ucc.leon

class GuestVoucher extends Voucher {

    Guest guest

    static belongsTo = [activity: Activity]
}
