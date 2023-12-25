package presentation.screens.creating.dayConcentration

import domain.model.DayConcentration
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import presentation.style.creatingWindowWidth
import presentation.views.DoubleValueTextField
import tornadofx.*
import java.time.LocalDate

class CreateDayConcentrationScreen : View() {
    private val _title = "Create Year Concentration"

    private val materialNameInputProperty = SimpleStringProperty()
    private val valueInputProperty = SimpleStringProperty()
    private val yearInputProperty = SimpleStringProperty()

    private val useCases = CreateDayConcentrationUseCases()

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

        text("Value")
        val valueTextField = DoubleValueTextField()
        add(valueTextField)

        button("Create") {
            action {
                if (yearInputProperty.value.toInt() in 1950..LocalDate.now().year) {
                    val concentration = DayConcentration(
                        materialName = materialNameInputProperty.value,
                        value = valueTextField.text.toDouble(),
                        year = yearInputProperty.value.toInt(),
                    )
                    useCases.createUseCase.execute(concentration) {
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