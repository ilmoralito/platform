package ni.edu.ucc.leon

class LocationTagLib {

    static defaultEncodeAs = [taglib: 'none']

    static namespace = 'location'

    def property = { attrs ->
        out << Helper.LOCATION_PROPERTIES.find { property ->
            property.english == attrs.property
        }.spanish
    }
}
