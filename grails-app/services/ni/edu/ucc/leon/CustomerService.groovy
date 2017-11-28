package ni.edu.ucc.leon

import grails.gorm.services.Service
import ni.edu.ucc.leon.customer.SaveCustomerCommand
import ni.edu.ucc.leon.customer.UpdateCustomerCommand

interface ICustomerService {

    List<Customer> findAll()

    Customer find(final Serializable id)

    Customer save(SaveCustomerCommand command)

    Customer update(UpdateCustomerCommand command)

    Customer delete(final Serializable id)
}

@Service(Customer)
abstract class CustomerService implements ICustomerService {

    @Override
    Customer save(SaveCustomerCommand command) {
        Customer customer = new Customer(name: command.name)
        Representative representative = new Representative(
            email: command.email,
            fullName: command.fullName,
            telephoneNumber: command.telephoneNumber,
            identificationCard: command.identificationCard,
            academicTitle: command.academicTitle
        )

        customer.representative = representative

        customer.save(failOnError: true)
    }

    @Override
    Customer update(UpdateCustomerCommand command) {
        Customer customer = find(command.id)
        if (customer) {
            customer.with {
                name = command.name
                representative.fullName = command.fullName
                representative.identificationCard = command.identificationCard
                representative.email = command.email
                representative.telephoneNumber = command.telephoneNumber
                representative.academicTitle = command.academicTitle
            }

            customer.save(flush: true, failOnError: true)
        }

        customer
    }
}
