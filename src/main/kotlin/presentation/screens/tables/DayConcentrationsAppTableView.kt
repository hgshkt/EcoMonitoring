package presentation.screens.tables

import domain.model.DayConcentration
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.Parent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import presentation.screens.creating.dayConcentration.CreateDayConcentrationScreen
import presentation.screens.tables.usecases.TableUseCases
import presentation.screens.tables.usecases.DayConcentrationTableViewUseCases
import presentation.views.buttons.SelectFileButton
import presentation.style.buttonSizeHeight
import presentation.style.buttonSizeWidth
import tornadofx.*

class DayConcentrationsAppTableView : AppTableView() {
    private val _title = "Day Concentrations"

    private val tableUseCases: TableUseCases = TableUseCases()

    private var useCases: DayConcentrationTableViewUseCases = DayConcentrationTableViewUseCases()

    override val root: Parent

    private var observableConcentrations: ObservableList<DayConcentration>

    private var selectedTableType = SimpleStringProperty(TableType.DAY_CONCENTRATIONS.tableName)

    init {
        val concentrations = tableUseCases.getDayConcentrationsFromRemoteRepositoryUseCase
            .execute().concentrations
        observableConcentrations = concentrations.toObservable()

        title = _title

        root = hbox {
            val table = tableview(observableConcentrations) {
                readonlyColumn("Id", DayConcentration::id)

                readonlyColumn("Material", DayConcentration::materialName)
                readonlyColumn("Year", DayConcentration::year)
                readonlyColumn("Value (mg/m^3)", DayConcentration::value)
                readonlyColumn("Carcinogenic risk", DayConcentration::carcinogenicRisk)
                readonlyColumn("Non carcinogenic risk", DayConcentration::nonCarcinogenicRisk)
                readonlyColumn("Organ", DayConcentration::organ)

                readonlyColumn("Delete", DayConcentration::id).cellFormat { concentrationId ->
                    graphic = hbox(spacing = 5) {
                        button("Delete") {
                            action {
                                useCases.delete.execute(rowItem.id)
                                update()
                            }
                        }
                    }
                }
//                readonlyColumn("Edit", YearConcentration::id).cellFormat { concentrationId ->
//                    graphic = hbox(spacing = 5) {
//                        button("Edit") {
//                            action {
//                                replaceWith(CreateYearConcentrationScreen::class)
//                            }
//                        }
//                    }
//                }
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
                button(TableType.DAY_CONCENTRATIONS.tableName) {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        replaceWith(DayConcentrationsAppTableView::class)
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

                button("Add concentrations") {
                    action {
                        find<CreateDayConcentrationScreen>().openWindow()
                        update()
                    }
                }

                button("Remove all concentrations") {
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
        val newData = tableUseCases.getDayConcentrationsFromRemoteRepositoryUseCase
            .execute().concentrations
        observableConcentrations.addAll(newData)
    }
}