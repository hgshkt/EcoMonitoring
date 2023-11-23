package presentation.screens.tables

import data.repository.pollutions.remote.PollutionMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.pollutions.PollutionsMySQLStorage
import domain.model.Enterprise
import domain.model.Pollution
import domain.useCases.get.GetPollutionsFromRemoteRepositoryUseCase
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.Parent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import presentation.screens.creating.createMaterial.CreateMaterialScreen
import presentation.screens.creating.createPollution.CreatePollutionScreen
import presentation.screens.tables.usecases.PollutionTableViewUseCases
import presentation.screens.tables.usecases.TableUseCases
import presentation.views.buttons.SelectFileButton
import presentation.views.buttons.buttonSizeHeight
import presentation.views.buttons.buttonSizeWidth
import tornadofx.*

class PollutionsAppTableView : AppTableView() {

    private val _title = "Pollutions"

    private val tableUseCases: TableUseCases = TableUseCases()

    private var useCases: PollutionTableViewUseCases = PollutionTableViewUseCases()

    override val root: Parent

    private var observablePollutions: ObservableList<Pollution>

    private var selectedTableType = SimpleStringProperty(TableType.ENTERPRISES.tableName)

    init {
        val pollutions = useCases.getPollutionsFromRemoteRepositoryUseCase.execute().pollutions
        observablePollutions = pollutions.toObservable()

        title = _title

        root = hbox {
            val table = tableview(observablePollutions) {
                readonlyColumn("Enterprise name", Pollution::enterpriseName)
                readonlyColumn("Material name", Pollution::materialName)
                readonlyColumn("Year", Pollution::year)
                readonlyColumn("MaterialAmount", Pollution::materialAmount)

                readonlyColumn("Delete", Pollution::enterpriseName).cellFormat { enterpriseName ->
                    graphic = hbox(spacing = 5) {
                        button("Delete") {
                            action {
                                val pollution = Pollution(
                                    enterpriseName = enterpriseName,
                                    materialName = rowItem.materialName,
                                    year = rowItem.year,
                                    materialAmount = rowItem.materialAmount
                                )
                                useCases.delete.execute(pollution)
                            }
                        }
                    }
                }
            }

            HBox.setHgrow(table, Priority.ALWAYS)

            vbox {
                prefWidth = buttonSizeWidth

                text("Tables:")

                button("enterprises") {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        replaceWith(EnterprisesAppTableView::class)
                    }
                }
                button("materials") {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        replaceWith(MaterialsAppTableView::class)
                    }
                }
                button("pollutions") {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        replaceWith(PollutionsAppTableView::class)
                    }
                }

                region {
                    prefHeight  = 30.0
                }

                text("Fill data from file")

                combobox(
                    values = listOf(
                        TableType.MATERIALS.tableName,
                        TableType.ENTERPRISES.tableName,
                        TableType.POLLUTION.tableName
                    ).toObservable(),
                    property = selectedTableType
                ) {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                }

                add(
                    child = SelectFileButton(
                        selectedTableType = selectedTableType,
                        tableUseCases = tableUseCases
                    )
                )

                region {
                    prefHeight  = 30.0
                }

                text("Other functions")

                button("Update"){
                    action {
                        update()
                    }
                }

                button("Add pollutions") {
                    action {
                        find<CreatePollutionScreen>().openWindow()
                    }
                }

                button("Remove all pollutions") {
                    action {
                        useCases.deleteAll.execute()
                    }
                }
            }
        }
    }

    private fun update() {
        observablePollutions.clear()
        val newData =  tableUseCases.getPollutionsFromRemoteRepositoryUseCase
            .execute().pollutions
        observablePollutions.addAll(newData)
    }
}