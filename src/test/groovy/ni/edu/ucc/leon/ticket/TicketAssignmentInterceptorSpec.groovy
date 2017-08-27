package ni.edu.ucc.leon.ticket


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TicketAssignmentInterceptor)
class TicketAssignmentInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test ticketAssignment interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"ticketAssignment")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
