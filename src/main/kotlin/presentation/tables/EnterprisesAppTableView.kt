package presentation.tables

import domain.model.Enterprise
import tornadofx.*

class EnterprisesAppTableView(
    private val enterprises: MutableList<Enterprise>
) : AppTableView() {

    override val root = vbox {
        tableview(enterprises.asObservable()) {
            readonlyColumn("Id", Enterprise::id)
            readonlyColumn("Name", Enterprise::name)
            readonlyColumn("Activity", Enterprise::activity)
            readonlyColumn("Belonging", Enterprise::belonging)
            readonlyColumn("Location", Enterprise::location)
        }
    }
}