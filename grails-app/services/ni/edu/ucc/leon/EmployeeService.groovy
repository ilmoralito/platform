package ni.edu.ucc.leon

import grails.gorm.services.Service

@Service(Employee)
interface EmployeeService {

    List<Employee> list()

    Employee find(Serializable id)

    Employee save(String fullName, String identityCard, String contract)

    Employee update(Serializable id, String fullName, String identityCard, String contract, Boolean enabled)
}
