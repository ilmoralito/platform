package ni.edu.ucc.leon.user

import grails.plugin.springsecurity.SpringSecurityService
import grails.validation.ValidationException
import groovy.transform.TypeCheckingMode

import ni.edu.ucc.leon.User

import ni.edu.ucc.leon.CoordinationService
import ni.edu.ucc.leon.UserService
import ni.edu.ucc.leon.RoleService

class UserController {
    CoordinationService coordinationService
    UserService userService
    RoleService roleService

    def create() {
        respond new User(params), model: [roleList: roleService.list()]
    }

    def save(UserSaveCommand command) {
        if (command.hasErrors()) {
            respond command.errors, model: [roleList: roleService.list()], view: 'create'
        } else {
            try {
                User user = userService.save(command.employeeId, command.email, command.roles)

                flash.message = 'Cuenta de usuario vinculada'
                redirect uri: "/employees/${user.employee.id}/", method: 'GET'
            } catch(ValidationException e) {
                respond e.errors, model: [roleList: roleService.list()], view: 'create'
            }
        }
    }

    def show(Long id) {
        respond id ? userService.find(id) : null
    }

    def edit(Long id) {
        if (id) {
            respond userService.find(id), model: [roleList: roleService.list()]
        } else {
            respond null
        }
    }

    def update(Long employeeId, Long id, Boolean enabled, String email) {
        try {
            User user = userService.update(id, email, enabled ?: false, params.list('roles'))

            flash.message = 'Usuario actualizado'
            redirect uri: "/employees/$employeeId", method: 'GET'
        } catch(ValidationException e) {
            respond e.errors, view: 'edit', model: [roleList: roleService.list()]
        }
    }
}
