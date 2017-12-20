package ni.edu.ucc.leon

class EmployeeVoucher extends Voucher {

    Employee employee

    static belongsTo = [activity: Activity]
}
