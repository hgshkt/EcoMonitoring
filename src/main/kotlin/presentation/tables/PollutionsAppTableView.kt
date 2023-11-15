package presentation.tables

import domain.model.Pollution
import tornadofx.*

class PollutionsAppTableView(
    private val pollutions: MutableList<Pollution>
) : AppTableView() {

    override val root = vbox {
        tableview(pollutions.asObservable()) {
            readonlyColumn("Id", Pollution::id)
            readonlyColumn("Enterprise Id", Pollution::enterpriseName)
            readonlyColumn("Material Id", Pollution::materialName)
            readonlyColumn("Year", Pollution::year)
            readonlyColumn("MaterialAmount", Pollution::materialAmount)
        }
    }
}