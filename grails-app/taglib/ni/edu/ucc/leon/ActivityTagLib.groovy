package ni.edu.ucc.leon

class ActivityTagLib {
    static defaultEncodeAs = [taglib: 'none']

    static namespace = 'activity'

    def state = { attrs ->
        switch(attrs.state) {
            case 'created':
                out << 'Creado'
            break
        }
    }
}
