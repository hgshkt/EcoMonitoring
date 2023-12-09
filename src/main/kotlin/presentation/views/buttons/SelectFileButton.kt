package presentation.views.buttons

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Button
import javafx.stage.FileChooser
import presentation.screens.tables.TableType
import presentation.screens.tables.usecases.TableUseCases
import tornadofx.*

class SelectFileButton(
    private var selectedTableType: SimpleStringProperty,
    private val tableUseCases: TableUseCases,
    private val action: () -> Unit
) : Button() {

    private val _text = "Select File"

    init {
        text = _text

        width = buttonSizeWidth
        height = buttonSizeHeight

        setOnAction {
            val file = chooseFile(
                "Select a File",
                filters = arrayOf(FileChooser.ExtensionFilter("Excel Files", "*.xlsx")),
                mode = FileChooserMode.Single
            )[0]

            file.apply {
                when (selectedTableType.value) {
                    TableType.MATERIALS.tableName -> {
                        tableUseCases.loadMaterialsFromExcelUseCase.execute(path)
                    }
                    TableType.ENTERPRISES.tableName -> {
                        tableUseCases.loadEnterprisesFromExcelUseCase.execute(path)
                    }
                    TableType.POLLUTION.tableName -> {
                        tableUseCases.loadPollutionsFromExcelUseCase.execute(path)
                    }
                    TableType.YEAR_CONCENTRATIONS.tableName -> {
                        tableUseCases.loadYearConcentrationsFromExcelUseCase.execute(path)
                    }
                }
                action.invoke()
            }
        }
    }
}