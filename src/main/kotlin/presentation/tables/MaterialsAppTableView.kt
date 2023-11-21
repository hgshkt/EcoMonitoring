package presentation.tables

import data.repository.materials.remote.MaterialMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import domain.model.Material
import domain.useCases.GetMaterialsFromRemoteRepositoryUseCase
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import javafx.stage.FileChooser
import presentation.usecases.TableUseCases
import presentation.views.buttons.SelectFileButton
import presentation.views.buttons.buttonSizeHeight
import presentation.views.buttons.buttonSizeWidth
import tornadofx.*

class MaterialsAppTableView : AppTableView() {

    private val tableUseCases: TableUseCases = TableUseCases()

    private var useCase: GetMaterialsFromRemoteRepositoryUseCase

    override val root: Parent

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

        root = hbox {
            tableview(materials.asObservable()) {
                readonlyColumn("Id", Material::id)
                readonlyColumn("Enterprise Id", Material::name)
                readonlyColumn("Material Id", Material::gdk)
                readonlyColumn("Year", Material::dangerClass)
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
            }
        }
    }
}