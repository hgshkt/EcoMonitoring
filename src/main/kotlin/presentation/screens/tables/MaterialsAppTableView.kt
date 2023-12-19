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
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import presentation.screens.creating.createEnterprise.CreateEnterpriseScreen
import presentation.screens.creating.createMaterial.CreateMaterialScreen
import presentation.screens.tables.usecases.MaterialTableViewUseCases
import presentation.screens.tables.usecases.TableUseCases
import presentation.views.buttons.SelectFileButton
import presentation.views.buttons.buttonSizeHeight
import presentation.views.buttons.buttonSizeWidth
import tornadofx.*

class MaterialsAppTableView : AppTableView() {

    private val _title = "Materials"
    private val tableUseCases: TableUseCases = TableUseCases()

    private var useCases: MaterialTableViewUseCases = MaterialTableViewUseCases()

    override val root: Parent

    private var observableMaterials: ObservableList<Material>

    private var selectedTableType = SimpleStringProperty(TableType.MATERIALS.tableName)

    init {
        val materials = useCases.getMaterialsFromRemoteRepositoryUseCase.execute().materials
        observableMaterials = materials.toObservable()

        title = _title

        root = hbox {
            val table = tableview(observableMaterials) {
                readonlyColumn("№", Material::id)
                readonlyColumn("Name", Material::name)
                readonlyColumn("Gdk", Material::gdk)
                readonlyColumn("Danger class", Material::dangerClass)
                readonlyColumn("RfC", Material::RfC)

                readonlyColumn("Delete", Material::id).cellFormat { id ->
                    graphic = hbox(spacing = 5) {
                        button("Delete") {
                            action {
                                useCases.delete.execute(id)
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
                button("Add materials") {
                    action {
                        find<CreateMaterialScreen>().openWindow()
                        update()
                    }
                }

                button("Remove all materials") {
                    action {
                        useCases.deleteAll.execute()
                        update()
                    }
                }
            }
        }
    }

    private fun update() {
        observableMaterials.clear()
        val newData = tableUseCases.getMaterialsFromRemoteRepositoryUseCase
            .execute().materials
        observableMaterials.addAll(newData)
    }
}