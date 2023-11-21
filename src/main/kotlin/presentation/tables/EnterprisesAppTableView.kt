package presentation.tables

import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import domain.model.Enterprise
import domain.useCases.GetEnterprisesFromRemoteRepositoryUseCase
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import javafx.stage.FileChooser
import presentation.usecases.TableUseCases
import presentation.views.buttons.SelectFileButton
import presentation.views.buttons.buttonSizeHeight
import presentation.views.buttons.buttonSizeWidth
import tornadofx.*

class EnterprisesAppTableView : AppTableView() {

    private var useCase: GetEnterprisesFromRemoteRepositoryUseCase
    private val tableUseCases: TableUseCases = TableUseCases()

    private var selectedTableType = SimpleStringProperty(TableType.ENTERPRISES.tableName)

    override val root: Parent

    init {
        useCase = GetEnterprisesFromRemoteRepositoryUseCase(
            repository = EnterpriseMySQLRepository(
                storage = EnterprisesMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        )

        val enterprises = useCase.execute().enterprises

        root = hbox {
            tableview(enterprises.asObservable()) {
                readonlyColumn("Id", Enterprise::id)
                readonlyColumn("Name", Enterprise::name)
                readonlyColumn("Activity", Enterprise::activity)
                readonlyColumn("Belonging", Enterprise::belonging)
                readonlyColumn("Location", Enterprise::location)
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
            }
        }
    }
}