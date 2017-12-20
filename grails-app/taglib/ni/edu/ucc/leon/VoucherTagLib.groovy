package ni.edu.ucc.leon

class VoucherTagLib {
    static defaultEncodeAs = [taglib:'html']

    static namespace = 'voucher'

    def getServices = { attrs ->
        List<Map> list = [
            [label: 'Desayuno', service: attrs.voucher.breakfast],
            [label: 'Almuerzo', service: attrs.voucher.lunch],
            [label: 'Cena', service: attrs.voucher.dinner],
            [label: 'Otros', service: attrs.voucher.others],
        ]

        List<String> summary = list.inject([]) { accumulator, currentValue ->
            if (currentValue.service) accumulator << currentValue.label

            accumulator
        }

        out << summary.join(', ')
    }
}
