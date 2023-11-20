package presentation

import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import presentation.tables.AppTableView
import presentation.tables.EnterprisesAppTableView
import presentation.tables.MaterialsAppTableView
import presentation.tables.PollutionsAppTableView
import presentation.usecases.MainViewUseCases
import tornadofx.*

class MainView : View() {
    private val enterprisesTableView = EnterprisesAppTableView()
    private val materialsTableView = MaterialsAppTableView()
    private val pollutionsTableView = PollutionsAppTableView()

    private var selectedTableType = SimpleStringProperty()

    private var table: AppTableView = enterprisesTableView

    private val useCases: MainViewUseCases = MainViewUseCases()

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
                action {
                    val dir = chooseDirectory("Select Target Directory")
                    dir?.apply {
                        when (selectedTableType.name) {
                            TableType.MATERIALS.name -> useCases
                                .loadMaterialsFromExcelUseCase.execute(dir.path)
                            TableType.ENTERPRISES.name -> useCases
                                .loadEnterprisesFromExcelUseCase.execute(dir.path)
                            TableType.POLLUTION.name -> useCases
                                .loadPollutionsFromExcelUseCase.execute(dir.path)
                        }
                    }
                }
            }
        }
    }
}

private enum class TableType(val tableName: String) {
    MATERIALS("materials"),
    ENTERPRISES("enterprises"),
    POLLUTION("pollutions")
}