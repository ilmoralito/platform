package ni.edu.ucc.leon

import grails.gorm.services.Service

interface IUserService {

    User find(Serializable id)

    List<User> list()

    User save(Serializable employeeId, String email, List<String> roleList)

    User update(Serializable id, String email, Boolean enabled, List<String> roleList)

    User delete(Serializable id)
}

@Service(User)
abstract class UserService implements IUserService {
    @Autowired RoleService roleService
    @Autowired EmployeeService employeeService

    @Override
    User save(Serializable employeeId, String email, List<String> roleList) {
        Employee employee = employeeService.find(employeeId)
        User user = new User(email: email)

        employee.user = user

        employee.save(flush: true)

        roleList.each { roleId ->
            Role role = roleService.find(roleId)

            if (role) {
                UserRole.create(user, role, true)
            }
        }

        user
    }

    @Override
    User update(Serializable id, String email, Boolean enabled, List<String> roleList) {
        User user = find(id)

        if (user) {
            user.email = email
            user.enabled = enabled

            user.save(flush: true)

            UserRole.removeAll(user)

            roleList.each { roleId ->
                Role role = roleService.find(roleId)

                if (role) {
                    UserRole.create(user, role, true)
                }
            }
        }

        user
    }
}
