package ni.edu.ucc.leon

class CoordinationTagLib {

    static defaultEncodeAs = [taglib:'html']

    static namespace = 'coordination'

    def area = { attrs ->
        if (attrs.area == 'academic') {
            out << 'Academica'
        }

        if (attrs.area == 'administrative') {
            out << 'Administrativa'
        }

        if (attrs.area == 'school') {
            out << 'Facultad'
        }
    }
}
