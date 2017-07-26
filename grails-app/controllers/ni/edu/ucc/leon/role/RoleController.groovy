package ni.edu.ucc.leon.role

import static org.springframework.http.HttpStatus.NOT_FOUND
import grails.validation.ValidationException
import grails.compiler.GrailsCompileStatic
import groovy.transform.TypeCheckingMode
import ni.edu.ucc.leon.RoleService
import ni.edu.ucc.leon.Role

class RoleController {
    RoleService roleService

    static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE',]

    def index() {
        respond roleService.list()
    }

    def save(String authority) {
        try {
            Role role = roleService.save(authority)

            flash.message = 'Role creado'
            redirect role
        } catch(ValidationException e) {
            respond e.errors, view: 'index', model: [roleList: roleService.list()]
        }
    }

    def show(Long id) {
        respond id ? roleService.find(id) : null
    }

    def edit(Long id) {
        respond id ? roleService.find(id) : null
    }

    def update(Long id, String authority) {
        try {
            Role role = roleService.update(id, authority)

            flash.message = 'Role actualizado'
            redirect role
        } catch(ValidationException e) {
            respond e.errors, view: 'edit'
        }
    }

    def delete(Long id) {
        Role role = roleService.delete(id)

        if (!role) {
            notFound()
        } else {
            flash.message = 'Rol eliminado'
            redirect uri: "/roles"
        }
    }

    protected notFound() {
        flash.message = 'Rol no encontrado'
        redirect uri: '/roles'
    }
}
