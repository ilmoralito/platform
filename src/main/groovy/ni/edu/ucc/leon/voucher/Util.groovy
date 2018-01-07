package ni.edu.ucc.leon.voucher

import groovy.transform.TypeCheckingMode
import groovy.transform.CompileStatic

import ni.edu.ucc.leon.Voucher

@CompileStatic
class Util {

    @CompileStatic(TypeCheckingMode.SKIP)
    private static final List<Map> buildService(final Voucher voucher) {
        [
            [label: 'Desayuno', value: voucher.breakfast],
            [label: 'Almuerzo', value: voucher.lunch],
            [label: 'Cena', value: voucher.dinner],
            [label: 'Otros', value: voucher.others]
        ]
    }

    @CompileStatic(TypeCheckingMode.SKIP)
    public static final String getServiceList(final Voucher voucher) {
        List<Map> services = buildService(voucher)

        String result = services.inject([], { accumulator, currentValue ->
            if (currentValue.value) accumulator << currentValue.label

            accumulator
        }).join(', ')

        result
    }
}
