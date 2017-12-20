package ni.edu.ucc.leon

import ni.edu.ucc.leon.voucher.SaveEmployeeVoucherCommand
import ni.edu.ucc.leon.EmployeeService
import ni.edu.ucc.leon.ActivityService
import grails.gorm.services.Service

interface IEmployeeVoucherService {

    List<EmployeeVoucher> findAllByActivity(final Serializable activityId)

    List<String> getVoucherSummary(final Serializable activityId)

    List<Employee> getEmployeeList(final Serializable activityId, final java.sql.Timestamp date)

    void delete(final Serializable id)

    Number save(SaveEmployeeVoucherCommand command)
}

@Service(EmployeeVoucher)
abstract class EmployeeVoucherService implements IEmployeeVoucherService {

    EmployeeService employeeService
    ActivityService activityService

    @Override
    List<EmployeeVoucher> findAllByActivity(final Serializable activityId) {
        Activity activity = activityService.find(activityId)

        EmployeeVoucher.where { activity == activity }.list()
    }

    @Override
    List<String> getVoucherSummary(final Serializable activityId) {
        List<EmployeeVoucher> employeeVoucherList = findAllByActivity(activityId).sort { it.employee.fullName }
        List<Map> summary = employeeVoucherList.groupBy { it.date }.collect {
            [
                date: it.key,
                vouchers: it.value.collect {
                    [
                        id: it.id,
                        toName: it.employee.fullName,
                        price: it.price,
                        services: [
                            [label: 'desayuno', service: it.breakfast],
                            [label: 'almuerzo', service: it.lunch],
                            [label: 'cena', service: it.dinner],
                            [label: 'otros', service: it.others]
                        ].inject([]) { accumulator, currentValue ->
                            if (currentValue.service) accumulator << currentValue.label

                            accumulator
                        }
                    ]
                }
            ]
        }.sort { it.date }

        summary
    }

    @Override
    List<Employee> getEmployeeList(final Serializable activityId, final java.sql.Timestamp date) {
        Activity activity = activityService.find(activityId)
        List<Employee> employeeList = employeeService.findAllByEnabled(true)
        List<Employee> employeeVoucherList = EmployeeVoucher.where { activity == activity && date == date }.list().employee

        employeeList - employeeVoucherList
    }

    @Override
    Number save(SaveEmployeeVoucherCommand command) {
        Activity activity = activityService.find(command.activityId)

        command.employees.each { employee ->
            EmployeeVoucher employeeVoucher = new EmployeeVoucher(
                employee: employee,
                date: command.date,
                coffeeShop: command.coffeeShop,
                price: command.price,
                coordination: activity.organizedBy,
                breakfast: command.breakfast,
                lunch: command.lunch,
                dinner: command.dinner,
                others: command.others
            )

            activity.addToEmployeeVouchers(employeeVoucher)

            employeeVoucher.save(failOnError: true)
        }

        command.employees.size()
    }
}
