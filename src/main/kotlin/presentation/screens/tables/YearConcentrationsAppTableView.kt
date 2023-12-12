package presentation.screens.tables

import domain.model.YearConcentration
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.Parent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import presentation.screens.creating.createPollution.CreatePollutionScreen
import presentation.screens.tables.usecases.TableUseCases
import presentation.screens.tables.usecases.YearConcentrationTableViewUseCases
import presentation.views.buttons.SelectFileButton
import presentation.views.buttons.buttonSizeHeight
import presentation.views.buttons.buttonSizeWidth
import tornadofx.*

class YearConcentrationsAppTableView : AppTableView() {
    private val _title = "Year Concentrations"

    private val tableUseCases: TableUseCases = TableUseCases()

    private var useCases: YearConcentrationTableViewUseCases = YearConcentrationTableViewUseCases()

    override val root: Parent

    private var observableConcentrations: ObservableList<YearConcentration>

    private var selectedTableType = SimpleStringProperty(TableType.YEAR_CONCENTRATIONS.tableName)

    init {
        val concentrations = tableUseCases.getYearConcentrationsFromRemoteRepositoryUseCase
            .execute().concentrations
        observableConcentrations = concentrations.toObservable()

        val materials = tableUseCases.getMaterialsFromRemoteRepositoryUseCase
            .execute().materials

        title = _title

        root = hbox {
            val table = tableview(observableConcentrations) {
                readonlyColumn("Id", YearConcentration::id)

                readonlyColumn("Material", YearConcentration::materialId).cellFormat {
                    val material = materials.find { material ->
                        material.id == rowItem.materialId
                    }!!
                    graphic = hbox {
                        text(material.name)
                    }
                }
                readonlyColumn("Year", YearConcentration::year)
                readonlyColumn("Value", YearConcentration::value)
                readonlyColumn("Carcinogenic risk", YearConcentration::carcinogenicRisk)
                readonlyColumn("Non carcinogenic risk", YearConcentration::nonCarcinogenicRisk)
                readonlyColumn("Organ", YearConcentration::organ)

                readonlyColumn("Delete", YearConcentration::id).cellFormat { concentrationId ->
                    graphic = hbox(spacing = 5) {
                        button("Delete") {
                            action {
                                useCases.delete.execute(rowItem.id)
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
                button(TableType.YEAR_CONCENTRATIONS.tableName) {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        replaceWith(YearConcentrationsAppTableView::class)
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
                        find<CreatePollutionScreen>().openWindow()
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
        val newData = tableUseCases.getYearConcentrationsFromRemoteRepositoryUseCase
            .execute().concentrations
        observableConcentrations.addAll(newData)
    }
}