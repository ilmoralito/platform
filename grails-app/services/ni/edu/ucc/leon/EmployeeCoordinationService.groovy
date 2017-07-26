package ni.edu.ucc.leon

import grails.gorm.services.Service

interface IEmployeeCoordinationService {

    List<EmployeeCoordination> listByEmployee(Employee employee)

    EmployeeCoordination find(Serializable id)

    EmployeeCoordination save(Serializable employeeId, Serializable coordinationId, String position, String jobTitle)

    EmployeeCoordination update(Serializable id, Employee employee, Coordination coordination, String position, String jobTitle)

    EmployeeCoordination delete(Serializable id)

    Number countManagersInCoordination(Serializable coordinationId)
}

@Service(EmployeeCoordination)
abstract class EmployeeCoordinationService implements IEmployeeCoordinationService {
    EmployeeService employeeService
    CoordinationService coordinationService

    @Override
    EmployeeCoordination save(Serializable employeeId, Serializable coordinationId, String position, String jobTitle) {
        Employee employee = employeeService.find(employeeId)
        Coordination coordination = coordinationService.find(coordinationId)

        EmployeeCoordination employeeCoordination = EmployeeCoordination.create(employee, coordination, position, jobTitle)

        if (employeeCoordination == null) {
            throw new IllegalArgumentException('Errors')
        }

        employeeCoordination
    }

    @Override
    Number countManagersInCoordination(Serializable coordinationId) {
        Coordination coordination = coordinationService.find(coordinationId)

        List result = EmployeeCoordination.executeQuery('''
            SELECT new map(COUNT(ec.position) AS count)
            FROM
                EmployeeCoordination ec
            WHERE
                ec.coordination = :coordination
                    AND ec.position = 'manager'
        ''', [coordination: coordination])

        result[0].count
    }
}
