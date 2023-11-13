package presentation

import domain.data.model.LoadedData
import presentation.events.ChangeViewEvent
import presentation.tables.EnterprisesAppTableView
import presentation.tables.MaterialsAppTableView
import presentation.tables.PollutionsAppTableView
import presentation.tables.AppTableView
import tornadofx.*

class MainView: View() {
    val data: LoadedData by params
//    private val enterprisesTableView = EnterprisesAppTableView(data.enterprises)
//    private val materialsTableView = MaterialsAppTableView(data.materials)
//    private val pollutionsTableView = PollutionsAppTableView(data.pollutions)
//
//    private var table: AppTableView = EnterprisesAppTableView(data.enterprises)

    override val root = vbox {
//        button("enterprises") {
//            action {
//                table = enterprisesTableView
//            }
//        }
//        button("materials") {
//            action {
//                table = materialsTableView
//            }
//        }
//        button("pollutions") {
//            action {
//                table = pollutionsTableView
//            }
//        }
//        table
    }
}