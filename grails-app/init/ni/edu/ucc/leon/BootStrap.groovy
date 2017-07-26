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
        Role adminRole = new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        Role userRole = new Role(authority: 'ROLE_USER').save(failOnError: true)

        User adminUser = new User(
            email: 'admin.user@ucc.edu.ni',
            password: 'password'
        ).save(failOnError: true)

        User anotherAdminUser = new User(
            email: 'anotheradmin.user@ucc.edu.ni',
            password: 'password'
        ).save(failOnError: true)

        User userUser = new User(
            email: 'user.user@ucc.edu.ni',
            password: 'password'
        ).save(failOnError: true)

        UserRole.create adminUser, adminRole
        UserRole.create anotherAdminUser, adminRole
        UserRole.create userUser, userRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 3
        assert Role.count() == 2
        assert UserRole.count() == 3

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

        assert Employee.count() == 4

        // COORDINATIONS
        Coordination warehouse = new Coordination(
            name: 'Bodega',
            extensionNumber: '113',
            copyFee: 100,
            area: 'administrative'
        ).save failOnError: true

        Coordination protocol = new Coordination(
            name: 'Protocolo',
            extensionNumber: '106',
            copyFee: 200,
            area: 'administrative'
        ).save failOnError: true

        Coordination technicalSupport = new Coordination(
            name: 'Soporte tecnico',
            extensionNumber: '129',
            copyFee: 50,
            area: 'administrative'
        ).save failOnError: true

        Coordination economicSciences = new Coordination(
            name: 'Ciencias Economicas',
            acronym: 'CCEE',
            extensionNumber: '130',
            copyFee: 650,
            area: 'school'
        ).save failOnError: true

        assert Coordination.count() == 4

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
        Career programming1 = new Career(name: 'Programacion 1').save failOnError: true
        Career drawing1 = new Career(name: 'Dibujo 1').save failOnError: true
        Career technicalWriting = new Career(name: 'Redaccion tecnica').save failOnError: true
        Career projectsFormulation = new Career(name: 'Formulacion de proyectos').save failOnError: true
        Career algorithms1 = new Career(name: 'Algoritmos 1').save failOnError: true
        Career math1 = new Career(name: 'Matematica 1').save failOnError: true

        assert Career.count() == 6

        // EMPLOYEE COORDINATIONS
        EmployeeCoordination userEmployeeCoordinations = new EmployeeCoordination(
            employee: employee1,
            coordination: warehouse,
            position: 'manager',
            jobTitle: 'Director de bodega'
        ).save failOnError: true

        EmployeeCoordination adminEmployeeCoordinations = new EmployeeCoordination(
            employee: employee2,
            coordination: technicalSupport,
            position: 'assistant',
            jobTitle: 'Asistente de soporte tecnico'
        ).save failOnError: true

        EmployeeCoordination anotherAdminEmployeeCoordinations = new EmployeeCoordination(
            employee: employee3,
            coordination: technicalSupport,
            position: 'manager',
            jobTitle: 'Responsable de soporte tecnico'
        ).save failOnError: true

        assert EmployeeCoordination.count() == 3

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
        // if(!Coordination.findByName('Soporte tecnico')) {
        //     new Coordination(
        //         name: 'Soporte tecnico',
        //         extensionNumber: '129',
        //         copyFee: 50,
        //         area: 'administrative'
        //     ).save(failOnError: true)
        // }

        // if (!Role.findByAuthority('ROLE_ADMIN')) {
        //     new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        // }
    }
}
