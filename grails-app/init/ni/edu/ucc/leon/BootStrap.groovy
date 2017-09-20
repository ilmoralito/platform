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
        Role userRole = new Role(authority: 'ROLE_USER').save(failOnError: true)
        Role adminRole = new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        Role protocolRole = new Role(authority: 'ROLE_PROTOCOL').save(failOnError: true)
        Role assistantRole = new Role(authority: 'ROLE_ASSISTANT').save(failOnError: true)
        Role coordinatorRole = new Role(authority: 'ROLE_COORDINATOR').save(failOnError: true)
        Role headOfficeDelegateRole = new Role(authority: 'ROLE_HEAD_OFFICE_DELEGATE').save(failOnError: true)
        Role academicCoordinatorRole = new Role(authority: 'ROLE_ACADEMIC_COORDINATOR').save(failOnError: true)
        Role administrativeCoordinatorRole = new Role(authority: 'ROLE_ADMINISTRATIVE_COORDINATOR').save(failOnError: true)

        User userUser = new User(email: 'user.user@ucc.edu.ni').save(failOnError: true)
        User adminUser = new User(email: 'admin.user@ucc.edu.ni').save(failOnError: true)
        User anotherAdminUser = new User(email: 'anotheradmin.user@ucc.edu.ni').save(failOnError: true)

        User protocolUser = new User(email: 'protocol.user@ucc.edu.ni').save(failOnError: true)
        User coordinatorUser = new User(email: 'coordinator.user@ucc.edu.ni').save(failOnError: true)
        User assistant1User = new User(email: 'assistant1.user@ucc.edu.ni').save(failOnError: true)
        User assistant2User = new User(email: 'assistant2.user@ucc.edu.ni').save(failOnError: true)
        User headOfficeDelegateUser = new User(email: 'headOfficeDelegate.user@ucc.edu.ni').save(failOnError: true)
        User academicCoordinatorUser = new User(email: 'academicCoordinator.user@ucc.edu.ni').save(failOnError: true)
        User administrativeCoordinatorUser = new User(email: 'administrativeCoordinator.user@ucc.edu.ni').save(failOnError: true)

        User employee5UserAccount = new User(email: 'employee.5@ucc.edu.ni').save failOnError: true
        User employee6UserAccount = new User(email: 'employee.6@ucc.edu.ni').save failOnError: true
        User employee7UserAccount = new User(email: 'employee.7@ucc.edu.ni').save failOnError: true

        UserRole.create userUser, userRole
        UserRole.create adminUser, adminRole
        UserRole.create anotherAdminUser, adminRole
        UserRole.create employee5UserAccount, userRole
        UserRole.create employee6UserAccount, userRole
        UserRole.create employee7UserAccount, userRole

        UserRole.create protocolUser, userRole
        UserRole.create protocolUser, protocolRole

        UserRole.create assistant1User, userRole
        UserRole.create assistant1User, assistantRole

        UserRole.create assistant2User, userRole
        UserRole.create assistant2User, assistantRole

        UserRole.create coordinatorUser, userRole
        UserRole.create coordinatorUser, coordinatorRole

        UserRole.create headOfficeDelegateUser, userRole
        UserRole.create headOfficeDelegateUser, headOfficeDelegateRole

        UserRole.create academicCoordinatorUser, userRole
        UserRole.create academicCoordinatorUser, academicCoordinatorRole

        UserRole.create administrativeCoordinatorUser, userRole
        UserRole.create administrativeCoordinatorUser, administrativeCoordinatorRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 13
        assert Role.count() == 8
        assert UserRole.count() == 20

        // EMPLOYEES
        Employee employee1 = new Employee(
            fullName: 'Employee one Full name',
            identityCard: '291-260780-0001W',
            user: userUser
        ).save(failOnError: true)

        Employee employee2 = new Employee(
            fullName: 'Employee two Full name',
            identityCard: '291-260784-0001W',
            user: adminUser
        ).save(failOnError: true)

        Employee employee3 = new Employee(
            fullName: 'Employee three Full name',
            identityCard: '291-240780-0001W',
            user: anotherAdminUser
        ).save(failOnError: true)

        Employee employee4 = new Employee(
            fullName: 'Employee four Full name',
            identityCard: '291-240790-0001W',
            contract: 'schedule'
        ).save(failOnError: true)

        Employee employee5 = new Employee(
            fullName: 'Employee five Full name',
            identityCard: '281-240890-0001W',
            user: employee5UserAccount
        ).save(failOnError: true)

        Employee employee6 = new Employee(
            fullName: 'Employee six Full name',
            identityCard: '281-250890-0001W',
            user: employee6UserAccount
        ).save(failOnError: true)

        Employee employee7 = new Employee(
            fullName: 'Employee seven Full name',
            identityCard: '281-200890-0001W',
            user: employee7UserAccount
        ).save(failOnError: true)

        Employee employee8 = new Employee(
            fullName: 'Employee Eight',
            identityCard: '281-290289-0001W',
            user: protocolUser
        ).save(failOnError: true)

        Employee employee9 = new Employee(
            fullName: 'Employee Nine',
            identityCard: '281-290285-0001W',
            user: coordinatorUser
        ).save(failOnError: true)

        Employee employee10 = new Employee(
            fullName: 'Employee Ten',
            identityCard: '281-290252-0001W',
            user: assistant1User
        ).save(failOnError: true)

        Employee employee11 = new Employee(
            fullName: 'Employee Eleven',
            identityCard: '281-290251-0001W',
            user: assistant2User
        ).save(failOnError: true)

        Employee employee12 = new Employee(
            fullName: 'Employee Twelve',
            identityCard: '281-290299-0001W',
            user: headOfficeDelegateUser
        ).save(failOnError: true)

        Employee employee13 = new Employee(
            fullName: 'Employee Thirteen',
            identityCard: '281-290552-0001W',
            user: academicCoordinatorUser
        ).save(failOnError: true)

        Employee employee14 = new Employee(
            fullName: 'Employee Fourteen',
            identityCard: '281-291252-0001W',
            user: administrativeCoordinatorUser
        ).save(failOnError: true)

        assert Employee.count() == 14

        // COORDINATIONS
        Coordination warehouse = new Coordination(
            name: 'Bodega',
            extensionNumber: '113',
            copyFee: 100,
            area: 'administrative'
        ).save(failOnError: true)

        Coordination protocol = new Coordination(
            name: 'Protocolo',
            extensionNumber: '216',
            copyFee: 200,
            area: 'administrative'
        ).save(failOnError: true)

        Coordination technicalSupport = new Coordination(
            name: 'Soporte tecnico',
            extensionNumber: '129',
            copyFee: 50,
            area: 'administrative'
        ).save(failOnError: true)

        Coordination economicSciences = new Coordination(
            name: 'Ciencias Economicas',
            acronym: 'CCEE',
            extensionNumber: '130',
            copyFee: 650,
            area: 'school'
        ).save(failOnError: true)

        Coordination continuingEducation = new Coordination(
            name: 'Educacion continua',
            extensionNumber: '112',
            copyFee: 220,
            area: 'academic'
        ).save(failOnError: true)

        Coordination studiesByMeeting = new Coordination(
            name: 'Estudios por encuentro',
            extensionNumber: '112',
            copyFee: 200,
            area: 'academic'
        ).save(failOnError: true)

        Coordination projects = new Coordination(
            name: 'Proyectos',
            extensionNumber: '112',
            copyFee: 100,
            area: 'academic'
        ).save(failOnError: true)

        Coordination headquartersDelegation = new Coordination(
            name: 'Delegacion de la sede',
            extensionNumber: '127',
            copyFee: 500,
            area: 'administrative'
        ).save(failOnError: true)

        Coordination academicDirection = new Coordination(
            name: 'Direccion academica',
            extensionNumber: '120',
            copyFee: 500,
            area: 'academic'
        ).save(failOnError: true)

        Coordination administrativeDirection = new Coordination(
            name: 'Direccion administrativa',
            extensionNumber: '111',
            copyFee: 400,
            area: 'administrative'
        ).save(failOnError: true)

        assert Coordination.count() == 10

        // CLASSROOMS
        Classroom C109A = new Classroom(
            code: 'C109A',
            name: 'Sala de reuniones',
            capacity: 50,
            wifi: true,
            airConditioned: true
        ).save failOnError: true

        Classroom mesanini1 = new Classroom(
            code: 'B201',
            name: 'Mesanini 1',
            capacity: 65,
            wifi: true,
            airConditioned: true
        ).save failOnError: true

        Classroom mesanini2 = new Classroom(
            code: 'B202',
            name: 'Mesanini 2',
            capacity: 70,
            wifi: true,
            airConditioned: true
        ).save failOnError: true

        Classroom D101 = new Classroom(code: 'D101').save failOnError: true

        assert Classroom.count() == 4

        // CAREERS
        new Career(name: 'Programacion 1').save failOnError: true
        new Career(name: 'Dibujo 1').save failOnError: true
        new Career(name: 'Redaccion tecnica').save failOnError: true
        new Career(name: 'Formulacion de proyectos').save failOnError: true
        new Career(name: 'Algoritmos 1').save failOnError: true
        new Career(name: 'Matematica 1').save failOnError: true

        assert Career.count() == 6

        // EMPLOYEE COORDINATIONS
        new EmployeeCoordination(
            employee: employee1,
            coordination: warehouse,
            position: 'manager',
            jobTitle: 'Director de bodega'
        ).save failOnError: true

        new EmployeeCoordination(
            employee: employee2,
            coordination: technicalSupport,
            position: 'assistant',
            jobTitle: 'Asistente de soporte tecnico'
        ).save failOnError: true

        new EmployeeCoordination(
            employee: employee3,
            coordination: technicalSupport,
            position: 'manager',
            jobTitle: 'Responsable de soporte tecnico'
        ).save failOnError: true

        new EmployeeCoordination(
            employee: employee5,
            coordination: continuingEducation,
            position: 'manager',
            jobTitle: 'Responsable'
        ).save failOnError: true

        new EmployeeCoordination(
            employee: employee5,
            coordination: studiesByMeeting,
            position: 'manager',
            jobTitle: 'Responsable'
        ).save failOnError: true

        new EmployeeCoordination(
            employee: employee5,
            coordination: projects,
            position: 'manager',
            jobTitle: 'Responsable'
        ).save failOnError: true

        new EmployeeCoordination(
            employee: employee6,
            coordination: continuingEducation,
            position: 'assistant',
            jobTitle: 'Colaborador'
        ).save failOnError: true

        new EmployeeCoordination(
            employee: employee6,
            coordination: studiesByMeeting,
            position: 'assistant',
            jobTitle: 'Colaborador'
        ).save failOnError: true

        new EmployeeCoordination(
            employee: employee7,
            coordination: continuingEducation,
            position: 'assistant',
            jobTitle: 'Colaborador'
        ).save failOnError: true

        new EmployeeCoordination(
            employee: employee7,
            coordination: studiesByMeeting,
            position: 'assistant',
            jobTitle: 'Colaborador'
        ).save failOnError: true

        new EmployeeCoordination(
            employee: employee8,
            coordination: protocol,
            position: 'manager',
            jobTitle: 'Coordinador de protocolo'
        ).save(failOnError: true)

        new EmployeeCoordination(
            employee: employee9,
            coordination: economicSciences,
            position: 'manager',
            jobTitle: 'Coordinador de ciencias economicas'
        ).save(failOnError: true)

        new EmployeeCoordination(
            employee: employee10,
            coordination: economicSciences,
            position: 'assistant',
            jobTitle: 'Asistente de ciencias economicas'
        ).save(failOnError: true)

        new EmployeeCoordination(
            employee: employee11,
            coordination: economicSciences,
            position: 'assistant',
            jobTitle: 'Asistente de ciencias economicas'
        ).save(failOnError: true)

        new EmployeeCoordination(
            employee: employee12,
            coordination: headquartersDelegation,
            position: 'manager',
            jobTitle: 'Delegada de la sede'
        ).save(failOnError: true)

        new EmployeeCoordination(
            employee: employee13,
            coordination: academicDirection,
            position: 'manager',
            jobTitle: 'Director academico'
        ).save(failOnError: true)

        new EmployeeCoordination(
            employee: employee14,
            coordination: administrativeDirection,
            position: 'manager',
            jobTitle: 'Director administrativo'
        ).save(failOnError: true)

        assert EmployeeCoordination.count() == 17

        // DEVICES
        List<String> deviceList = ['Network', 'Display', 'Keyboard', 'Mouse', 'UPS', 'Printer', 'Scanner']

        deviceList.each { device ->
            new Device(name: device).save failOnError: true
        }

        assert Device.count() == deviceList.size()

        // DATASHOWS
        new Datashow(
            trademark: 'EPSON',
            model: 'H692A',
            serialNumber: 'VU3K6402339',
            code: 1,
            hdmi: true,
            wifi: true,
            ethernet: true,
            enabled: true
        ).save(failOnError: true)

        new Datashow(
            trademark: 'EPSON',
            model: 'H573A',
            serialNumber: 'U3SK4400328',
            code: 2,
            hdmi: true,
            wifi: true,
            ethernet: true,
            enabled: true
        ).save(failOnError: true)

        new Datashow(
            trademark: 'EPSON',
            model: 'H430A',
            serialNumber: 'PSPK2302445',
            code: 3
        ).save(failOnError: true)

        new Datashow(
            trademark: 'EPSON',
            model: 'H430A',
            serialNumber: 'PSPK2302494',
            code: 4
        ).save(failOnError: true)

        new Datashow(
            trademark: 'BENQ',
            model: 'MS521P',
            serialNumber: 'PD6CD01995000',
            code: 5,
            hdmi: true
        ).save(failOnError: true)

        assert Datashow.count() == 5

        // HOLIDAYS
        Holiday mothersDay = new Holiday (
            date: new Date().parse('yyyy-MM-dd', '1940-05-30'),
            name: 'Dia de las madres'
        ).save(failOnError: true)

        Holiday dayTriumphOfRevolution = new Holiday(
            date: new Date().parse('yyyy-MM-dd', '1979-07-19'),
            name: 'Dia del triunfo de la revolucion',
            wiki: 'https://es.wikipedia.org/wiki/Revoluci%C3%B3n_Sandinista'
        ).save(failOnError: true)

        Holiday laborDay = new Holiday(
            date: new Date().parse('yyyy-MM-dd', '1886-05-1'),
            name: 'Dia del trabajador',
            wiki: 'https://es.wikipedia.org/wiki/D%C3%ADa_Internacional_de_los_Trabajadores'
        ).save(failOnError: true)

        Holiday sanJacintoBattle = new Holiday(
            date: new Date().parse('yyyy-MM-dd', '1856-09-14'),
            name: 'Batalla de San Jacinto',
            wiki: 'https://es.wikipedia.org/wiki/Batalla_de_San_Jacinto_(1856)'
        ).save(failOnError: true)

        Holiday independenceDay = new Holiday(
            date: new Date().parse('yyyy-MM-dd', '1821-09-15'),
            name: 'Dia de la independencia',
            wiki: 'http://www.confidencial.com.ni/archivos/articulo/1608/la-verdadera-fecha-de-la-independencia-de-nicaragua'
        ).save(failOnError: true)
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
