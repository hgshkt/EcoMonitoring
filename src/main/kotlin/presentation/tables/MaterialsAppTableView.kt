package presentation.tables

import data.repository.materials.remote.MaterialMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import domain.model.Material
import domain.useCases.GetMaterialsFromRemoteRepositoryUseCase
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import presentation.TableType
import presentation.usecases.TableUseCases
import tornadofx.*

class MaterialsAppTableView : AppTableView() {

    private val tableUseCases: TableUseCases = TableUseCases()

    private var useCase: GetMaterialsFromRemoteRepositoryUseCase

    override val root: Parent

    private var selectedTableType = SimpleStringProperty()

    init {
        useCase = GetMaterialsFromRemoteRepositoryUseCase(
            repository = MaterialMySQLRepository(
                storage = MaterialMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        )

        val materials = useCase.execute().materials

        root = hbox {
            tableview(materials.asObservable()) {
                readonlyColumn("Id", Material::id)
                readonlyColumn("Enterprise Id", Material::name)
                readonlyColumn("Material Id", Material::gdk)
                readonlyColumn("Year", Material::dangerClass)
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