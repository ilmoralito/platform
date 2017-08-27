package ni.edu.ucc.leon.ticket


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(EmployeeTicketInterceptor)
class EmployeeTicketInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test employeeTicket interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"employeeTicket")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
