package ni.edu.ucc.leon

import groovy.transform.CompileStatic

@CompileStatic
class Helper {
    static List<String> months = [
        'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
    ]

    static List<LinkedHashMap> statesList = [
        [english: 'open', spanish: 'Abiertos'],
        [english: 'pending', spanish: 'En progreso'],
        [english: 'closed', spanish: 'Cerrados']
    ]
}