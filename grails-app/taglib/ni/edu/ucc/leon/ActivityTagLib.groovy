package ni.edu.ucc.leon

class ActivityTagLib {
    static defaultEncodeAs = [taglib: 'none']

    static namespace = 'activity'

    def state = { attrs ->
        switch(attrs.currentState) {
            case 'created':
                out << 'Creado'
            break
            case 'notified':
                out << 'Notificado'
            break
            case 'confirmed':
                out << 'Confirmado'
            break
            case 'approved':
                out << 'Aprovado'
            break
            case 'authorized':
                out << 'Autorizado'
            break
            case 'canceled':
                out << 'Cancelado'
            break
        }
    }
}
