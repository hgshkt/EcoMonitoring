package presentation.screens.tables

import data.repository.materials.remote.MaterialMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import domain.model.Enterprise
import domain.model.Material
import domain.useCases.get.GetMaterialsFromRemoteRepositoryUseCase
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.Parent
import presentation.screens.creating.createEnterprise.CreateEnterpriseScreen
import presentation.screens.creating.createMaterial.CreateMaterialScreen
import presentation.screens.tables.usecases.TableUseCases
import presentation.views.buttons.SelectFileButton
import presentation.views.buttons.buttonSizeHeight
import presentation.views.buttons.buttonSizeWidth
import tornadofx.*

class MaterialsAppTableView : AppTableView() {

    private val tableUseCases: TableUseCases = TableUseCases()

    private var useCase: GetMaterialsFromRemoteRepositoryUseCase

    override val root: Parent

    private var observableMaterials: ObservableList<Material>

    private var selectedTableType = SimpleStringProperty(TableType.ENTERPRISES.tableName)

    init {
        useCase = GetMaterialsFromRemoteRepositoryUseCase(
            repository = MaterialMySQLRepository(
                storage = MaterialMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        )

        val materials = useCase.execute().materials
        observableMaterials = materials.toObservable()

        root = hbox {
            tableview(observableMaterials) {
                readonlyColumn("Id", Material::id)
                readonlyColumn("Enterprise Id", Material::name)
                readonlyColumn("Material Id", Material::gdk)
                readonlyColumn("Year", Material::dangerClass)

                readonlyColumn("Delete", Material::id).cellFormat { id ->
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
                button("Add materials") {
                    action {
                        find<CreateMaterialScreen>().openWindow()
                    }
                }
            }
        }
    }

    private fun update() {
        observableMaterials.clear()
        val newData =  tableUseCases.getMaterialsFromRemoteRepositoryUseCase
            .execute().materials
        observableMaterials.addAll(newData)
    }
}