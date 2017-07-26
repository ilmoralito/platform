package ni.edu.ucc.leon

import grails.gorm.services.Service

@Service(Role)
interface RoleService {

    List<Role> list()

    Role find(Serializable id)

    Role save(String authority)

    Role update(Serializable id, String authority)

    Role delete(Serializable id)
}
