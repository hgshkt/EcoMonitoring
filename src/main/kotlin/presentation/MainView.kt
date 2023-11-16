package presentation

import domain.useCases.loadData
import javafx.scene.Parent
import presentation.tables.EnterprisesAppTableView
import presentation.tables.MaterialsAppTableView
import presentation.tables.PollutionsAppTableView
import presentation.tables.AppTableView
import tornadofx.*
import util.pathToResources

class MainView : View() {
    private val enterprisesTableView = EnterprisesAppTableView()
    private val materialsTableView = MaterialsAppTableView()
    private val pollutionsTableView = PollutionsAppTableView()

    private var table: AppTableView = enterprisesTableView

    override val root: Parent

    init {
        root = vbox {
            button("enterprises") {
                action {
                    table = enterprisesTableView
                }
            }
            button("materials") {
                action {
                    table = materialsTableView
                }
            }
            button("pollutions") {
                action {
                    table = pollutionsTableView
                }
            }
            table.root
        }
    }
}