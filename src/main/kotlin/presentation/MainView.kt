package presentation

import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import presentation.tables.AppTableView
import presentation.tables.EnterprisesAppTableView
import presentation.tables.MaterialsAppTableView
import presentation.tables.PollutionsAppTableView
import tornadofx.*

class MainView : View() {
    private val enterprisesTableView = EnterprisesAppTableView()
    private val materialsTableView = MaterialsAppTableView()
    private val pollutionsTableView = PollutionsAppTableView()

    private var selectedTableType = SimpleStringProperty()

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
            combobox(
                values = listOf(
                    TableType.MATERIALS.tableName,
                    TableType.ENTERPRISES.tableName,
                    TableType.POLLUTION.tableName
                ).toObservable(),
                property = selectedTableType
            )

            button("Select File") {
                
            }
        }
    }
}

private enum class TableType(val tableName: String) {
    MATERIALS("materials"),
    ENTERPRISES("enterprises"),
    POLLUTION("pollutions")
}