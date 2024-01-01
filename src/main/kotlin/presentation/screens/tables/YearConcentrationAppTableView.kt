package presentation.screens.tables

import domain.model.YearConcentration
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.Parent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import presentation.controllers.CreateEnterpriseController
import presentation.screens.creating.createEnterprise.CreateEnterpriseScreen
import presentation.screens.tables.usecases.TableUseCases
import presentation.screens.tables.usecases.YearConcentrationTableViewUseCases
import presentation.style.buttonSizeHeight
import presentation.style.buttonSizeWidth
import presentation.views.buttons.SelectFileButton
import tornadofx.*

class YearConcentrationAppTableView : AppTableView() {

    private val _title = "Year concentrations"
    private var useCases: YearConcentrationTableViewUseCases = YearConcentrationTableViewUseCases()
    private val tableUseCases: TableUseCases = TableUseCases()

    private var selectedTableType = SimpleStringProperty(TableType.YEAR_CONCENTRATIONS.tableName)

    private var observableConcentrations: ObservableList<YearConcentration>

    override val root: Parent

    init {
        val concentrations = useCases.getAll.execute()
        observableConcentrations = concentrations.toObservable()

        title = _title

        root = hbox {
            val table = tableview(observableConcentrations) {
                readonlyColumn("Material", YearConcentration::materialName)
                readonlyColumn("Year", YearConcentration::year)
                readonlyColumn("Value", YearConcentration::value)

                readonlyColumn("Delete", YearConcentration::id).cellFormat { id ->
                    graphic = hbox(spacing = 5) {
                        button("Delete") {
                            action {
                                useCases.delete.execute(id)
                                update()
                            }
                        }
                    }
                }
            }

            HBox.setHgrow(table, Priority.ALWAYS)

            vbox {

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
                button(TableType.YEAR_CONCENTRATIONS.tableName) {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        replaceWith(YearConcentrationAppTableView::class)
                    }
                }

                region {
                    prefHeight = 30.0
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
                    prefHeight = 30.0
                }

                text("Other functions")

                button("Update") {
                    action {
                        update()
                    }
                }

                button("Add enterprise") {
                    action {
                        val controller = find(CreateEnterpriseController::class)
                        controller.id = 0
                        controller.name = ""
                        controller.activity = ""
                        controller.belonging = ""
                        controller.location = ""
                        controller.edit = false
                        find<CreateEnterpriseScreen>(mapOf(CreateEnterpriseController::class to controller)).openWindow()
                        update()
                    }
                }

                button("Remove all enterprises") {
                    action {
                        useCases.deleteAll.execute()
                        update()
                    }
                }
            }
        }
    }

    private fun update() {
        observableConcentrations.clear()
        val newData = tableUseCases
            .getYearConcentrationsFromRemoteRepositoryUseCase
            .execute()
        observableConcentrations.addAll(newData)
    }
}