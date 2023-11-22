package presentation.screens.tables

import data.repository.pollutions.remote.PollutionMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.pollutions.PollutionsMySQLStorage
import domain.model.Pollution
import domain.useCases.get.GetPollutionsFromRemoteRepositoryUseCase
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.Parent
import presentation.screens.creating.createMaterial.CreateMaterialScreen
import presentation.screens.creating.createPollution.CreatePollutionScreen
import presentation.screens.tables.usecases.TableUseCases
import presentation.views.buttons.SelectFileButton
import presentation.views.buttons.buttonSizeHeight
import presentation.views.buttons.buttonSizeWidth
import tornadofx.*

class PollutionsAppTableView : AppTableView() {

    private val tableUseCases: TableUseCases = TableUseCases()

    private var useCase: GetPollutionsFromRemoteRepositoryUseCase

    override val root: Parent

    private var observablePollutions: ObservableList<Pollution>

    private var selectedTableType = SimpleStringProperty(TableType.ENTERPRISES.tableName)

    init {
        useCase = GetPollutionsFromRemoteRepositoryUseCase(
            repository = PollutionMySQLRepository(
                storage = PollutionsMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        )

        val pollutions = useCase.execute().pollutions
        observablePollutions = pollutions.toObservable()

        root = hbox {
            tableview(observablePollutions) {
                readonlyColumn("Enterprise name", Pollution::enterpriseName)
                readonlyColumn("Material name", Pollution::materialName)
                readonlyColumn("Year", Pollution::year)
                readonlyColumn("MaterialAmount", Pollution::materialAmount)
            }

            vbox {
                prefWidth = buttonSizeWidth
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

                button("Add pollutions") {
                    action {
                        find<CreatePollutionScreen>().openWindow()
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