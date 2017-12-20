package ni.edu.ucc.leon

import grails.gorm.services.Service

interface IEmployeeService {

    List<Employee> list()

    Employee find(final Serializable id)

    List<Employee> findAllByEnabled(final Boolean enabled)

    Employee save(final String fullName, final String identityCard, final String contract)

    Employee update(final Serializable id, final String fullName, final String identityCard, final String contract, final Boolean enabled)
}

@Service(Employee)
abstract class EmployeeService implements IEmployeeService {
    Employee update(final Serializable id, final String fullName, final String identityCard, final String contract, final Boolean enabled) {
        Employee employee = find(id)

        if (employee != null) {
            employee.fullName = fullName
            employee.identityCard = identityCard
            employee.contract = contract
            employee.enabled = enabled
            employee.user.enabled = enabled

            employee.save(failOnError: true)
        }

        employee
    }
}
