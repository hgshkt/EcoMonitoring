package presentation.tables

import data.repository.pollutions.remote.PollutionMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.pollutions.PollutionsMySQLStorage
import domain.model.Pollution
import domain.useCases.GetPollutionsFromRemoteRepositoryUseCase
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import javafx.stage.FileChooser
import presentation.usecases.TableUseCases
import presentation.views.buttons.buttonSizeHeight
import presentation.views.buttons.buttonSizeWidth
import tornadofx.*
import tornadofx.vbox

class PollutionsAppTableView : AppTableView() {

    private val tableUseCases: TableUseCases = TableUseCases()

    private var useCase: GetPollutionsFromRemoteRepositoryUseCase

    override val root: Parent

    private var selectedTableType = SimpleStringProperty(TableType.ENTERPRISES.name)

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

                button("Select File") {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        val file = chooseFile(
                            "Select a File",
                            filters = arrayOf(FileChooser.ExtensionFilter("Excel Files", "*.xlsx")),
                            mode = FileChooserMode.Single
                        )[0]

                        file.apply {
                            when (selectedTableType.name) {
                                TableType.MATERIALS.name -> tableUseCases
                                    .loadMaterialsFromExcelUseCase.execute(path)

                                TableType.ENTERPRISES.name -> tableUseCases
                                    .loadEnterprisesFromExcelUseCase.execute(path)

                                TableType.POLLUTION.name -> tableUseCases
                                    .loadPollutionsFromExcelUseCase.execute(path)
                            }
                        }
                    }
                }
            }
        }
    }
}