package presentation.tables

import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import domain.model.Enterprise
import domain.useCases.GetEnterprisesFromRemoteRepositoryUseCase
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import presentation.TableType
import presentation.usecases.TableUseCases
import tornadofx.*

class EnterprisesAppTableView: AppTableView() {

    private var useCase: GetEnterprisesFromRemoteRepositoryUseCase
    private val tableUseCases: TableUseCases = TableUseCases()

    private var selectedTableType = SimpleStringProperty()

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
                    action {
                        replaceWith(EnterprisesAppTableView::class)
                    }
                }
                button("materials") {
                    action {
                        replaceWith(MaterialsAppTableView::class)
                    }
                }
                button("pollutions") {
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
                )

                button("Select File") {
                    action {
                        val dir = chooseDirectory("Select Target Directory")
                        dir?.apply {
                            when (selectedTableType.name) {
                                TableType.MATERIALS.name -> tableUseCases
                                    .loadMaterialsFromExcelUseCase.execute(dir.path)

                                TableType.ENTERPRISES.name -> tableUseCases
                                    .loadEnterprisesFromExcelUseCase.execute(dir.path)

                                TableType.POLLUTION.name -> tableUseCases
                                    .loadPollutionsFromExcelUseCase.execute(dir.path)
                            }
                        }
                    }
                }
            }
        }
    }
}