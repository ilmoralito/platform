package ni.edu.ucc.leon.datashow

import static org.springframework.http.HttpStatus.NOT_FOUND
import grails.validation.ValidationException
import ni.edu.ucc.leon.DatashowService
import ni.edu.ucc.leon.Datashow

class DatashowController {
    @Autowired DatashowService datashowService

    static allowedMethods = [save: 'POST', update: 'PUT']

    def index() {
        respond datashowService.list()
    }

    def create() {
        respond new Datashow(params)
    }

    def save(String trademark, String model, String serialNumber, Integer code, Boolean hdmi, Boolean wifi, Boolean ethernet) {
        try {
            Datashow datashow = datashowService.save(trademark, model, serialNumber, code, hdmi ?: false, wifi ?: false, ethernet ?: false)

            flash.message = 'Cañon creado'
            redirect datashow
        } catch(ValidationException e) {
            respond e.errors, view: 'create'
        }
    }

    def show(Long id) {
        respond id ? datashowService.find(id) : null
    }

    def edit(Long id) {
        respond id ? datashowService.find(id) : null
    }

    def update(Long id, String trademark, String model, String serialNumber, Integer code, Boolean hdmi, Boolean wifi, Boolean ethernet, Boolean enabled) {
        try {
            Datashow datashow = datashowService.update(id, trademark, model, serialNumber, code, hdmi ?: false, wifi ?: false, ethernet ?: false, enabled ?: false)

            if(!datashow) {
                notFound()
            } else {
                flash.message = 'Cañon actualizado'
                redirect datashow
            }
        } catch(ValidationException e) {
            respond e.errors, view: 'edit'
        }
    }

    protected void notFound() {
        flash.message = 'Cañon no encontrado'
        redirect uri: '/datashows', status: NOT_FOUND
    }
}
