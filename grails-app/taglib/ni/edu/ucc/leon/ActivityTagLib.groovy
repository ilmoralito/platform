package ni.edu.ucc.leon

class ActivityTagLib {

    ActivityStateService activityStateService
    ActivityService activityService

    static defaultEncodeAs = [taglib: 'none']

    static namespace = 'activity'

    def isValid = { attrs, body ->
        Activity activity = activityService.find(attrs.activityId)

        if (activity.state == 'created' && activityStateService.isActivityCreator(activity)) {
            out << body()
        }

        if (activity.state == 'notified' && activityStateService.isValidAuthority('ROLE_ACADEMIC_COORDINATOR') && activityStateService.isValidCoordination(activity.organizedBy)) {
            out << body()
        }

        if (activity.state in ['notified', 'confirmed'] && activityStateService.isValidAuthority('ROLE_ACADEMIC_DIRECTOR')) {
            out << body()
        }

        if (activity.state in ['notified', 'approved'] && activityStateService.isValidAuthority('ROLE_ADMINISTRATIVE_DIRECTOR')) {
            out << body()
        }
    }

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
            case 'attended':
                out << 'Atendido'
            break
            case 'canceled':
                out << 'Cancelado'
            break
        }
    }
}
