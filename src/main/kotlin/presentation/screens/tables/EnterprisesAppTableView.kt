package presentation.screens.tables

import domain.model.Enterprise
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.Parent
import presentation.screens.creating.createEnterprise.CreateEnterpriseScreen
import presentation.screens.tables.usecases.EnterpriseTableViewUseCases
import presentation.screens.tables.usecases.TableUseCases
import presentation.views.buttons.SelectFileButton
import presentation.views.buttons.buttonSizeHeight
import presentation.views.buttons.buttonSizeWidth
import tornadofx.*

class EnterprisesAppTableView : AppTableView() {

    private val _title = "Enterprises"
    private var useCases: EnterpriseTableViewUseCases = EnterpriseTableViewUseCases()
    private val tableUseCases: TableUseCases = TableUseCases()

    private var selectedTableType = SimpleStringProperty(TableType.ENTERPRISES.tableName)

    private var observableEnterprises: ObservableList<Enterprise>

    override val root: Parent

    init {
        val enterprises = useCases.getEnterprisesFromRemoteRepositoryUseCase.execute().enterprises
        observableEnterprises = enterprises.toObservable()

        title = _title

        root = hbox {
            tableview(observableEnterprises) {
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
                            }
                        }
                    }
                }
            }

            vbox {

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

                button("Update"){
                    action {
                        update()
                    }
                }

                button("Add enterprise") {
                    action {
                        find<CreateEnterpriseScreen>().openWindow()
                    }
                }

                button("Remove all enterprises") {
                    action {
                        useCases.deleteAll.execute()
                    }
                }
            }
        }
    }

    private fun update() {
        observableEnterprises.clear()
        val newData =  tableUseCases.getEnterprisesFromRemoteRepositoryUseCase
            .execute().enterprises
        observableEnterprises.addAll(newData)
    }
}