package presentation.screens.creating.createYearConcentration

import domain.model.Pollution
import domain.model.YearConcentration
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import presentation.style.creatingWindowWidth
import presentation.views.textField.DoubleValueTextField
import tornadofx.*
import java.time.LocalDate

class CreateYearConcentrationScreen : View() {
    private val _title = "Create Year Concentration"

    private val materialNameInputProperty = SimpleStringProperty()
    private val yearInputProperty = SimpleStringProperty()

    private val useCases = CreateYearConcentrationScreenUseCases()

    private var materialNames: List<String> = listOf()

    override val root = vbox {
        prefWidth = creatingWindowWidth.toDouble()

        materialNames = useCases
            .getMaterialsFromRemoteRepositoryUseCase
            .execute()
            .materials
            .map {
                it.name
            }

        text("materialName")
        combobox(
            property = materialNameInputProperty,
            values = materialNames
        )

        text("year (1950 - ${LocalDate.now().year})")
        textfield(yearInputProperty)

        text("value")
        val valueTextField = DoubleValueTextField()
        add(valueTextField)

        button("Create") {
            action {
                if (yearInputProperty.value.toInt() in 1950..LocalDate.now().year) {
                    val concentration = YearConcentration(
                        id = -1,
                        materialName = materialNameInputProperty.value,
                        year = yearInputProperty.value.toInt(),
                        value = valueTextField.text.toDouble()
                    )
                    useCases.createYearConcentration.execute(concentration) {
                        sqlException()
                    }
                    close()
                } else {
                    alert(Alert.AlertType.ERROR, "Error", "Input year 1950 - ${LocalDate.now().year}")
                }
            }
        }
    }

    private fun sqlException() {
        alert(Alert.AlertType.ERROR, "SQL Error", "Pollution material id is not sent to the material")
    }

    init {
        title = _title
    }
}