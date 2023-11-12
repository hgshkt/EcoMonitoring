package presentation.tables

import domain.model.Pollution
import javafx.scene.Parent
import javafx.scene.control.TableView
import tornadofx.*

class PollutionsTableView(
    private val pollutions: MutableList<Pollution>
) : View() {
    private val pollutionTable = TableView<Pollution>()

    override val root: Parent
    init {
        with(pollutionTable) {
            columns.add(column("Name", Pollution::class))
            columns.add(column("Year", Pollution::class))
            columns.add(column("Value", Pollution::class))
            items.setAll(pollutions)
        }

        root = hbox {
            add(pollutionTable)
        }
    }
}