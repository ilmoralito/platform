package ni.edu.ucc.leon.user

// import org.springframework.security.providers.encoding.PasswordEncoder
import grails.plugin.springsecurity.SpringSecurityService
import grails.compiler.GrailsCompileStatic
import grails.validation.Validateable

// @GrailsCompileStatic
class ChangePasswordCommand implements Validateable {
    SpringSecurityService springSecurityService
    def passwordEncoder

    String currentPassword
    String newPassword
    String repeatNewPassword

    static constraints = {
        currentPassword validator: { String currentPassword, ChangePasswordCommand obj ->
            String currentUserPassword = obj.springSecurityService.currentUser.password

            obj.passwordEncoder.isPasswordValid(currentUserPassword, currentPassword, null)
        }

        repeatNewPassword validator: { String repeatNewPassword, ChangePasswordCommand obj ->
            repeatNewPassword == obj.newPassword
        }
    }
}