package presentation.controllers

import tornadofx.Controller
import tornadofx.property

class CreateEnterpriseController: Controller() {
    var id: Int by property()
    var name: String by property()
    var activity: String by property()
    var belonging: String by property()
    var location: String by property()
    var edit: Boolean by property()
}
