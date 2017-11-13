package ni.edu.ucc.leon

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        if (Environment.current == Environment.DEVELOPMENT) {
            log.info 'Loading development data'
            development()
        }

        if (Environment.current == Environment.PRODUCTION) {
            log.info 'Loading production data...'
            production()
        }
    }
    def destroy = {
    }

    private void development() {
        // ROLES
        Role userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        Role adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        Role protocolRole = Role.findByAuthority('ROLE_PROTOCOL') ?: new Role(authority: 'ROLE_PROTOCOL').save(failOnError: true)
        Role assistantRole = Role.findByAuthority('ROLE_ASSISTANT') ?: new Role(authority: 'ROLE_ASSISTANT').save(failOnError: true)
        Role coordinatorRole = Role.findByAuthority('ROLE_COORDINATOR') ?: new Role(authority: 'ROLE_COORDINATOR').save(failOnError: true)
        Role headOfficeDelegateRole = Role.findByAuthority('ROLE_HEAD_OFFICE_DELEGATE') ?: new Role(authority: 'ROLE_HEAD_OFFICE_DELEGATE').save(failOnError: true)
        Role academicCoordinatorRole = Role.findByAuthority('ROLE_ACADEMIC_COORDINATOR') ?: new Role(authority: 'ROLE_ACADEMIC_COORDINATOR').save(failOnError: true)
        Role administrativeCoordinatorRole = Role.findByAuthority('ROLE_ADMINISTRATIVE_COORDINATOR') ?: new Role(authority: 'ROLE_ADMINISTRATIVE_COORDINATOR').save(failOnError: true)

        // USERS
        User userUser = User.findByEmail('user.user@ucc.edu.ni') ?: new User(email: 'user.user@ucc.edu.ni').save(failOnError: true)
        User adminUser = User.findByEmail('admin.user@ucc.edu.ni') ?: new User(email: 'admin.user@ucc.edu.ni').save(failOnError: true)
        User anotherAdminUser = User.findByEmail('anotheradmin.user@ucc.edu.ni') ?: new User(email: 'anotheradmin.user@ucc.edu.ni').save(failOnError: true)

        User protocolUser = User.findByEmail('protocol.user@ucc.edu.ni') ?: new User(email: 'protocol.user@ucc.edu.ni').save(failOnError: true)
        User coordinatorUser = User.findByEmail('coordinator.user@ucc.edu.ni') ?: new User(email: 'coordinator.user@ucc.edu.ni').save(failOnError: true)
        User assistant1User = User.findByEmail('assistant1.user@ucc.edu.ni') ?: new User(email: 'assistant1.user@ucc.edu.ni').save(failOnError: true)
        User assistant2User = User.findByEmail('assistant2.user@ucc.edu.ni') ?: new User(email: 'assistant2.user@ucc.edu.ni').save(failOnError: true)
        User headOfficeDelegateUser = User.findByEmail('headOfficeDelegate.user@ucc.edu.ni') ?: new User(email: 'headOfficeDelegate.user@ucc.edu.ni').save(failOnError: true)
        User academicCoordinatorUser = User.findByEmail('academicCoordinator.user@ucc.edu.ni') ?: new User(email: 'academicCoordinator.user@ucc.edu.ni').save(failOnError: true)
        User administrativeCoordinatorUser = User.findByEmail('administrativeCoordinator.user@ucc.edu.ni') ?: new User(email: 'administrativeCoordinator.user@ucc.edu.ni').save(failOnError: true)

        User employee5UserAccount = User.findByEmail('employee.5@ucc.edu.ni') ?: new User(email: 'employee.5@ucc.edu.ni').save(failOnError: true)
        User employee6UserAccount = User.findByEmail('employee.6@ucc.edu.ni') ?: new User(email: 'employee.6@ucc.edu.ni').save(failOnError: true)
        User employee7UserAccount = User.findByEmail('employee.7@ucc.edu.ni') ?: new User(email: 'employee.7@ucc.edu.ni').save(failOnError: true)

        // USER ROLES
        if (!UserRole.exists(userUser.id, userRole.id)) {
            UserRole.create userUser, userRole
        }

        if (!UserRole.exists(adminUser.id, adminRole.id)) {
            UserRole.create adminUser, adminRole
        }

        if (!UserRole.exists(anotherAdminUser.id, adminRole.id)) {
            UserRole.create anotherAdminUser, adminRole
        }

        if (!UserRole.exists(employee5UserAccount.id, userRole.id)) {
            UserRole.create employee5UserAccount, userRole
        }

        if (!UserRole.exists(employee6UserAccount.id, userRole.id)) {
            UserRole.create employee6UserAccount, userRole
        }

        if (!UserRole.exists(employee7UserAccount.id, userRole.id)) {
            UserRole.create employee7UserAccount, userRole
        }

        if (!UserRole.exists(protocolUser.id, userRole.id)) {
            UserRole.create protocolUser, userRole
        }

        if (!UserRole.exists(protocolUser.id, protocolRole.id)) {
            UserRole.create protocolUser, protocolRole
        }

        if (!UserRole.exists(assistant1User.id, userRole.id)) {
            UserRole.create assistant1User, userRole
        }

        if (!UserRole.exists(assistant1User.id, assistantRole.id)) {
            UserRole.create assistant1User, assistantRole
        }

        if (!UserRole.exists(assistant2User.id, userRole.id)) {
            UserRole.create assistant2User, userRole
        }

        if (!UserRole.exists(assistant2User.id, assistantRole.id)) {
            UserRole.create assistant2User, assistantRole
        }

        if (!UserRole.exists(coordinatorUser.id, userRole.id)) {
            UserRole.create coordinatorUser, userRole
        }

        if (!UserRole.exists(coordinatorUser.id, coordinatorRole.id)) {
            UserRole.create coordinatorUser, coordinatorRole
        }

        if (!UserRole.exists(headOfficeDelegateUser.id, userRole.id)) {
            UserRole.create headOfficeDelegateUser, userRole
        }

        if (!UserRole.exists(headOfficeDelegateUser.id, headOfficeDelegateRole.id)) {
            UserRole.create headOfficeDelegateUser, headOfficeDelegateRole
        }

        if (!UserRole.exists(academicCoordinatorUser.id, userRole.id)) {
            UserRole.create academicCoordinatorUser, userRole
        }

        if (!UserRole.exists(academicCoordinatorUser.id, academicCoordinatorRole.id)) {
            UserRole.create academicCoordinatorUser, academicCoordinatorRole
        }

        if (!UserRole.exists(administrativeCoordinatorUser.id, userRole.id)) {
            UserRole.create administrativeCoordinatorUser, userRole
        }

        if (!UserRole.exists(administrativeCoordinatorUser.id, administrativeCoordinatorRole.id)) {
            UserRole.create administrativeCoordinatorUser, administrativeCoordinatorRole
        }

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 13
        assert Role.count() == 8
        assert UserRole.count() == 20

        // EMPLOYEES
        Employee employee1 = Employee.findByIdentityCard('291-260780-0001W') ?: new Employee(
            fullName: 'Employee one Full name',
            identityCard: '291-260780-0001W',
            user: userUser
        ).save(failOnError: true)

        Employee employee2 = Employee.findByIdentityCard('291-260784-0001W') ?: new Employee(
            fullName: 'Employee two Full name',
            identityCard: '291-260784-0001W',
            user: adminUser
        ).save(failOnError: true)

        Employee employee3 = Employee.findByIdentityCard('291-240780-0001W') ?: new Employee(
            fullName: 'Employee three Full name',
            identityCard: '291-240780-0001W',
            user: anotherAdminUser
        ).save(failOnError: true)

        Employee employee4 = Employee.findByIdentityCard('291-240790-0001W') ?: new Employee(
            fullName: 'Employee four Full name',
            identityCard: '291-240790-0001W',
            contract: 'schedule'
        ).save(failOnError: true)

        Employee employee5 = Employee.findByIdentityCard('281-240890-0001W') ?: new Employee(
            fullName: 'Employee five Full name',
            identityCard: '281-240890-0001W',
            user: employee5UserAccount
        ).save(failOnError: true)

        Employee employee6 = Employee.findByIdentityCard('281-250890-0001W') ?: new Employee(
            fullName: 'Employee six Full name',
            identityCard: '281-250890-0001W',
            user: employee6UserAccount
        ).save(failOnError: true)

        Employee employee7 = Employee.findByIdentityCard('281-200890-0001W') ?: new Employee(
            fullName: 'Employee seven Full name',
            identityCard: '281-200890-0001W',
            user: employee7UserAccount
        ).save(failOnError: true)

        Employee employee8 = Employee.findByIdentityCard('281-290289-0001W') ?: new Employee(
            fullName: 'Employee Eight',
            identityCard: '281-290289-0001W',
            user: protocolUser
        ).save(failOnError: true)

        Employee employee9 = Employee.findByIdentityCard('281-290285-0001W') ?: new Employee(
            fullName: 'Employee Nine',
            identityCard: '281-290285-0001W',
            user: coordinatorUser
        ).save(failOnError: true)

        Employee employee10 = Employee.findByIdentityCard('281-290252-0001W') ?: new Employee(
            fullName: 'Employee Ten',
            identityCard: '281-290252-0001W',
            user: assistant1User
        ).save(failOnError: true)

        Employee employee11 = Employee.findByIdentityCard('281-290251-0001W') ?: new Employee(
            fullName: 'Employee Eleven',
            identityCard: '281-290251-0001W',
            user: assistant2User
        ).save(failOnError: true)

        Employee employee12 = Employee.findByIdentityCard('281-290299-0001W') ?: new Employee(
            fullName: 'Employee Twelve',
            identityCard: '281-290299-0001W',
            user: headOfficeDelegateUser
        ).save(failOnError: true)

        Employee employee13 = Employee.findByIdentityCard('281-290552-0001W') ?: new Employee(
            fullName: 'Employee Thirteen',
            identityCard: '281-290552-0001W',
            user: academicCoordinatorUser
        ).save(failOnError: true)

        Employee employee14 = Employee.findByIdentityCard('281-291252-0001W') ?: new Employee(
            fullName: 'Employee Fourteen',
            identityCard: '281-291252-0001W',
            user: administrativeCoordinatorUser
        ).save(failOnError: true)

        assert Employee.count() == 14

        // COORDINATIONS
        Coordination warehouse = Coordination.findByName('Bodega') ?: new Coordination(
            name: 'Bodega',
            extensionNumber: '113',
            copyFee: 100,
            area: 'administrative'
        ).save(failOnError: true)

        Coordination protocol = Coordination.findByName('Protocolo') ?: new Coordination(
            name: 'Protocolo',
            extensionNumber: '216',
            copyFee: 200,
            area: 'administrative'
        ).save(failOnError: true)

        Coordination technicalSupport = Coordination.findByName('Soporte tecnico') ?: new Coordination(
            name: 'Soporte tecnico',
            extensionNumber: '129',
            copyFee: 50,
            area: 'administrative'
        ).save(failOnError: true)

        Coordination economicSciences = Coordination.findByName('Ciencias Economicas') ?: new Coordination(
            name: 'Ciencias Economicas',
            acronym: 'CCEE',
            extensionNumber: '130',
            copyFee: 650,
            area: 'school'
        ).save(failOnError: true)

        Coordination continuingEducation = Coordination.findByName('Educacion continua') ?: new Coordination(
            name: 'Educacion continua',
            extensionNumber: '112',
            copyFee: 220,
            area: 'academic'
        ).save(failOnError: true)

        Coordination studiesByMeeting = Coordination.findByName('Estudios por encuentro') ?: new Coordination(
            name: 'Estudios por encuentro',
            extensionNumber: '112',
            copyFee: 200,
            area: 'academic'
        ).save(failOnError: true)

        Coordination projects = Coordination.findByName('Proyectos') ?: new Coordination(
            name: 'Proyectos',
            extensionNumber: '112',
            copyFee: 100,
            area: 'academic'
        ).save(failOnError: true)

        Coordination headquartersDelegation = Coordination.findByName('Delegacion de la sede') ?: new Coordination(
            name: 'Delegacion de la sede',
            extensionNumber: '127',
            copyFee: 500,
            area: 'administrative'
        ).save(failOnError: true)

        Coordination academicDirection = Coordination.findByName('Direccion academica') ?: new Coordination(
            name: 'Direccion academica',
            extensionNumber: '120',
            copyFee: 500,
            area: 'academic'
        ).save(failOnError: true)

        Coordination administrativeDirection = Coordination.findByName('Direccion administrativa') ?: new Coordination(
            name: 'Direccion administrativa',
            extensionNumber: '111',
            copyFee: 400,
            area: 'administrative'
        ).save(failOnError: true)

        // EMPLOYEE COORDINATIONS
        if (!EmployeeCoordination.exists(employee1, warehouse)) {
            new EmployeeCoordination(
                employee: employee1,
                coordination: warehouse,
                position: 'manager',
                jobTitle: 'Director de bodega'
            ).save failOnError: true
        }

        if (!EmployeeCoordination.exists(employee2, technicalSupport)) {
            new EmployeeCoordination(
                employee: employee2,
                coordination: technicalSupport,
                position: 'assistant',
                jobTitle: 'Asistente de soporte tecnico'
            ).save failOnError: true
        }

        if (!EmployeeCoordination.exists(employee3, technicalSupport)) {
            new EmployeeCoordination(
                employee: employee3,
                coordination: technicalSupport,
                position: 'manager',
                jobTitle: 'Responsable de soporte tecnico'
            ).save failOnError: true
        }

        if (!EmployeeCoordination.exists(employee5, continuingEducation)) {
            new EmployeeCoordination(
                employee: employee5,
                coordination: continuingEducation,
                position: 'manager',
                jobTitle: 'Responsable'
            ).save failOnError: true
        }

        if (!EmployeeCoordination.exists(employee5, studiesByMeeting)) {
            new EmployeeCoordination(
                employee: employee5,
                coordination: studiesByMeeting,
                position: 'manager',
                jobTitle: 'Responsable'
            ).save failOnError: true
        }

        if (!EmployeeCoordination.exists(employee5, projects)) {
            new EmployeeCoordination(
                employee: employee5,
                coordination: projects,
                position: 'manager',
                jobTitle: 'Responsable'
            ).save failOnError: true
        }

        if (!EmployeeCoordination.exists(employee6, continuingEducation)) {
            new EmployeeCoordination(
                employee: employee6,
                coordination: continuingEducation,
                position: 'assistant',
                jobTitle: 'Colaborador'
            ).save failOnError: true
        }

        if (!EmployeeCoordination.exists(employee6, studiesByMeeting)) {
            new EmployeeCoordination(
                employee: employee6,
                coordination: studiesByMeeting,
                position: 'assistant',
                jobTitle: 'Colaborador'
            ).save failOnError: true
        }

        if (!EmployeeCoordination.exists(employee7, continuingEducation)) {
            new EmployeeCoordination(
                employee: employee7,
                coordination: continuingEducation,
                position: 'assistant',
                jobTitle: 'Colaborador'
            ).save failOnError: true
        }

        if (!EmployeeCoordination.exists(employee7, studiesByMeeting)) {
            new EmployeeCoordination(
                employee: employee7,
                coordination: studiesByMeeting,
                position: 'assistant',
                jobTitle: 'Colaborador'
            ).save failOnError: true
        }

        if (!EmployeeCoordination.exists(employee8, protocol)) {
            new EmployeeCoordination(
                employee: employee8,
                coordination: protocol,
                position: 'manager',
                jobTitle: 'Coordinador de protocolo'
            ).save(failOnError: true)
        }

        if (!EmployeeCoordination.exists(employee9, economicSciences)) {
            new EmployeeCoordination(
                employee: employee9,
                coordination: economicSciences,
                position: 'manager',
                jobTitle: 'Coordinador de ciencias economicas'
            ).save(failOnError: true)
        }

        if (!EmployeeCoordination.exists(employee10, economicSciences)) {
            new EmployeeCoordination(
                employee: employee10,
                coordination: economicSciences,
                position: 'assistant',
                jobTitle: 'Asistente de ciencias economicas'
            ).save(failOnError: true)
        }

        if (!EmployeeCoordination.exists(employee11, economicSciences)) {
            new EmployeeCoordination(
                employee: employee11,
                coordination: economicSciences,
                position: 'assistant',
                jobTitle: 'Asistente de ciencias economicas'
            ).save(failOnError: true)
        }

        if (!EmployeeCoordination.exists(employee12, headquartersDelegation)) {
            new EmployeeCoordination(
                employee: employee12,
                coordination: headquartersDelegation,
                position: 'manager',
                jobTitle: 'Delegada de la sede'
            ).save(failOnError: true)
        }

        if (!EmployeeCoordination.exists(employee13, academicDirection)) {
            new EmployeeCoordination(
                employee: employee13,
                coordination: academicDirection,
                position: 'manager',
                jobTitle: 'Director academico'
            ).save(failOnError: true)
        }

        if (!EmployeeCoordination.exists(employee14, administrativeDirection)) {
            new EmployeeCoordination(
                employee: employee14,
                coordination: administrativeDirection,
                position: 'manager',
                jobTitle: 'Director administrativo'
            ).save(failOnError: true)
        }

        assert EmployeeCoordination.count() == 17

        // DEVICES
        List<String> deviceList = ['Network', 'Display', 'Keyboard', 'Mouse', 'UPS', 'Printer', 'Scanner']

        deviceList.each { device ->
            if (!Device.findByName(device)) {
                new Device(name: device).save failOnError: true
            }
        }

        assert Device.count() == deviceList.size()

        // ACTIVITIES
        if (!AcademicActivity.count()) {
            AcademicActivity academicActivity1 = new AcademicActivity(
                name: 'Lorem ipsum dolor sit amet',
                organizedBy: economicSciences,
                employee: employee10
            ).save(failOnError: true)

            AcademicActivity academicActivity2 = new AcademicActivity(
                name: 'Lorem ipsum dolor sit amet',
                organizedBy: economicSciences,
                employee: employee10
            ).save(failOnError: true)

            AcademicActivity academicActivity3 = new AcademicActivity(
                name: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit',
                organizedBy: economicSciences,
                employee: employee10
            ).save(failOnError: true)
        }
    }

    private void production() {
        Coordination technicalSupport = Coordination.findByName('Soporte tecnico')
        if (!technicalSupport) {
            technicalSupport = new Coordination(
                name: 'Soporte tecnico',
                extensionNumber: '129',
                copyFee: 50,
                area: 'administrative'
            ).save(failOnError: true)
        }

        Role role = Role.findByAuthority('ROLE_ADMIN')
        if (!role) {
            role = new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        }

        Employee employee = Employee.findByIdentityCard('291-290180-0001W')
        if (!employee) {
            employee = new Employee(
                fullName: 'Mario Martinez',
                identityCard: '291-290180-0001W'
            ).save(failOnError: true)

            EmployeeCoordination.create(employee, technicalSupport, 'assistant', 'Asistente de soporte tecnico')
        }

        User user = User.findByEmail('mario.martinez@ucc.edu.ni')
        if (!user) {
            user = new User(email: 'mario.martinez@ucc.edu.ni').save(failOnError: true)

            UserRole.create(user, role, true)

            employee.user = user
            employee.save(failOnError: true)
        }
    }
}
