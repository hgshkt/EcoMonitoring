package presentation

import domain.model.data.remote.LoadedData
import domain.useCases.loadData
import javafx.scene.Parent
import presentation.tables.EnterprisesAppTableView
import presentation.tables.MaterialsAppTableView
import presentation.tables.PollutionsAppTableView
import presentation.tables.AppTableView
import tornadofx.*
import util.pathToResources

class MainView : View() {
    private var data: LoadedData = loadData(
        materialsExcelFileName = pathToResources("речовини.xlsx"),
        pollutionsExcelFileName = pathToResources("викиди.xlsx"),
        enterprisesExcelFileName = pathToResources("підприємства.xlsx")
    )
    private val enterprisesTableView = EnterprisesAppTableView(data.enterprises)
    private val materialsTableView = MaterialsAppTableView(data.materials)
    private val pollutionsTableView = PollutionsAppTableView(data.pollutions)

    private var table: AppTableView = EnterprisesAppTableView(data.enterprises)

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
            MyLabel().root
        }
    }
}

class MyLabel: View() {
    override val root = label("qwer")
}