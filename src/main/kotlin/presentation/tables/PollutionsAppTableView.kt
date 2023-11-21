package presentation.tables

import data.repository.pollutions.remote.PollutionMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.pollutions.PollutionsMySQLStorage
import domain.model.Pollution
import domain.useCases.GetPollutionsFromRemoteRepositoryUseCase
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import presentation.TableType
import presentation.usecases.TableUseCases
import tornadofx.*
import tornadofx.vbox

class PollutionsAppTableView : AppTableView() {

    private val tableUseCases: TableUseCases = TableUseCases()

    private var useCase: GetPollutionsFromRemoteRepositoryUseCase

    override val root: Parent

    private var selectedTableType = SimpleStringProperty()

    init {
        useCase = GetPollutionsFromRemoteRepositoryUseCase(
            repository = PollutionMySQLRepository(
                storage = PollutionsMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        )

        val pollutions = useCase.execute().pollutions

        root = hbox {
            tableview(pollutions.asObservable()) {
                readonlyColumn("Enterprise name", Pollution::enterpriseName)
                readonlyColumn("Material name", Pollution::materialName)
                readonlyColumn("Year", Pollution::year)
                readonlyColumn("MaterialAmount", Pollution::materialAmount)
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