package presentation.screens.tables

import domain.model.Enterprise
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.Parent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import presentation.controllers.CreateEnterpriseController
import presentation.screens.creating.createEnterprise.CreateEnterpriseScreen
import presentation.screens.tables.usecases.EnterpriseTableViewUseCases
import presentation.screens.tables.usecases.TableUseCases
import presentation.views.buttons.SelectFileButton
import presentation.style.buttonSizeHeight
import presentation.style.buttonSizeWidth
import tornadofx.*

class EnterprisesAppTableView : AppTableView() {

    private val _title = "Enterprises"
    private var useCases: EnterpriseTableViewUseCases = EnterpriseTableViewUseCases()
    private val tableUseCases: TableUseCases = TableUseCases()

    private var selectedTableType = SimpleStringProperty(TableType.ENTERPRISES.tableName)

    private var observableEnterprises: ObservableList<Enterprise>

    override val root: Parent

    init {
        val enterprises = useCases.getAll.execute().enterprises
        observableEnterprises = enterprises.toObservable()

        title = _title

        root = hbox {
            val table = tableview(observableEnterprises) {
                readonlyColumn("Id", Enterprise::id)
                readonlyColumn("Name", Enterprise::name)
                readonlyColumn("Activity", Enterprise::activity)
                readonlyColumn("Belonging", Enterprise::belonging)
                readonlyColumn("Location", Enterprise::location)

                readonlyColumn("Delete", Enterprise::id).cellFormat { id ->
                    graphic = hbox(spacing = 5) {
                        button("Delete") {
                            action {
                                useCases.delete.execute(id)
                                update()
                            }
                        }
                    }
                }
//                readonlyColumn("Edit", Enterprise::id).cellFormat { enterprise ->
//                    graphic = hbox(spacing = 5) {
//                        button("Edit") {
//                            action {
//                                val controller = find(CreateEnterpriseController::class)
//                                controller.id = rowItem.id
//                                controller.name = rowItem.name
//                                controller.activity = rowItem.activity
//                                controller.belonging = rowItem.belonging
//                                controller.location = rowItem.location
//                                controller.edit = true
//                                find<CreateEnterpriseScreen>(CreateEnterpriseController::class to controller).openWindow()
//                            }
//                        }
//                    }
//                }
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
        observableEnterprises.clear()
        val newData = tableUseCases.getEnterprisesFromRemoteRepositoryUseCase
            .execute().enterprises
        observableEnterprises.addAll(newData)
    }
}