package ni.edu.ucc.leon

class RepresentativeTagLib {

    static defaultEncodeAs = [taglib: 'html']

    static namespace = 'representative'

    def renderTelephoneNumber = { attrs ->
        String token1 = attrs.telephoneNumber.substring(0, 4)
        String token2 = attrs.telephoneNumber.substring(4, 8)

        out << token1 + " " + token2
    }
}
