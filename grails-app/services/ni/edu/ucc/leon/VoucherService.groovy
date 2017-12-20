package ni.edu.ucc.leon

import grails.gorm.services.Service

@Service(Guest)
interface VoucherService {

    Voucher find(final Serializable id)

    Voucher delete(final Serializable id)
}
