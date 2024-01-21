package presentation.views.buttons

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.stage.FileChooser
import presentation.screens.tables.TableType
import presentation.screens.tables.usecases.TableUseCases
import presentation.style.buttonSizeHeight
import presentation.style.buttonSizeWidth
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
            try {
                val file = chooseFile(
                    "Select a File",
                    filters = arrayOf(FileChooser.ExtensionFilter("Excel Files", "*.xlsx")),
                    mode = FileChooserMode.Single
                )[0]

                file.apply {
                    try {
                        when (selectedTableType.value) {
                            TableType.MATERIALS.tableName -> {
                                tableUseCases.loadMaterialsFromExcelUseCase.execute(path)
                            }

                            TableType.ENTERPRISES.tableName -> {
                                tableUseCases.loadEnterprisesFromExcelUseCase.execute(path)
                            }

                            TableType.POLLUTION.tableName -> {
                                tableUseCases.loadPollutionsFromExcelUseCase.execute(
                                    filePath = path,
                                    materialNotFoundException = {materialNotFoundException()},
                                    enterpriseNotFoundException = { enterpriseNotFoundException()}
                                )
                            }

                            TableType.YEAR_CONCENTRATIONS.tableName -> {
                                tableUseCases.loadYearConcentrationsFromExcelUseCase.execute(path)
                            }

                            TableType.DAMAGE_DATA.tableName -> {
                                tableUseCases.loadDamageDataFromExcelUseCase.execute(path)
                            }

                            TableType.TAX_RATES.tableName -> {
                                tableUseCases.loadTaxRatesFromExcelUseCase.execute(path)
                            }
                        }
                    } catch (exception: Exception) {
                        alert(Alert.AlertType.ERROR, "Error", "SQL Exception")
                    }
                    action.invoke()
                }
            } catch (e: Exception) {
                alert(Alert.AlertType.ERROR, "Error", "File not selected")
            }
        }
    }

    private fun materialNotFoundException() {
        alert(Alert.AlertType.ERROR, "Error", "Materials not found in table")
    }

    private fun enterpriseNotFoundException() {
        alert(Alert.AlertType.ERROR, "Error", "Enterprises not found in table")
    }
}