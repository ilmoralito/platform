package ni.edu.ucc.leon.employee


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(EmployeeInterceptor)
class EmployeeInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test employee interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"employee")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
