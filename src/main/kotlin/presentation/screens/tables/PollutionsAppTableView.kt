package presentation.screens.tables

import domain.model.Pollution
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.Parent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import presentation.screens.creating.createPollution.CreatePollutionScreen
import presentation.screens.tables.usecases.PollutionTableViewUseCases
import presentation.screens.tables.usecases.TableUseCases
import presentation.views.buttons.SelectFileButton
import presentation.style.buttonSizeHeight
import presentation.style.buttonSizeWidth
import tornadofx.*

class PollutionsAppTableView : AppTableView() {

    private val _title = "Pollutions"

    private val tableUseCases: TableUseCases = TableUseCases()

    private var useCases: PollutionTableViewUseCases = PollutionTableViewUseCases()

    override val root: Parent

    private var observablePollutions: ObservableList<Pollution>

    private var selectedTableType = SimpleStringProperty(TableType.POLLUTION.tableName)

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
                                    materialAmount = rowItem.materialAmount,
                                    concentration = rowItem.concentration
                                )
                                useCases.delete.execute(pollution)
                                update()
                            }
                        }
                    }
                }
            }

            HBox.setHgrow(table, Priority.ALWAYS)

            vbox {
                prefWidth = buttonSizeWidth

                text("Tables:")

                button(TableType.ENTERPRISES.tableName) {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        replaceWith(EnterprisesAppTableView::class)
                    }
                }
                button(TableType.MATERIALS.tableName) {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        replaceWith(MaterialsAppTableView::class)
                    }
                }
                button(TableType.POLLUTION.tableName) {
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
                    values = TableType.entries.map { it.tableName },
                    property = selectedTableType
                ) {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                }

                add(
                    child = SelectFileButton(
                        selectedTableType = selectedTableType,
                        tableUseCases = tableUseCases,
                        action = { update() }
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
                        update()
                    }
                }

                button("Remove all pollutions") {
                    action {
                        useCases.deleteAll.execute()
                        update()
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