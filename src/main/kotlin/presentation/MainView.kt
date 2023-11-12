package presentation

import presentation.events.ChangeViewEvent
import presentation.tables.EnterprisesTableView
import presentation.tables.MaterialsTableView
import presentation.tables.PollutionsTableView
import tornadofx.*

class MainView: View() {

    private val enterprisesTableView = EnterprisesTableView()
    private val materialsTableView = MaterialsTableView()
    private val pollutionsTableView = PollutionsTableView()

    override val root = vbox {
        button("enterprises") {
            action {
                fire(ChangeViewEvent(enterprisesTableView))
            }
        }
        button("materials") {
            action {
                fire(ChangeViewEvent(materialsTableView))
            }
        }
        button("pollutions") {
            action {
                fire(ChangeViewEvent(pollutionsTableView))
            }
        }
    }

}