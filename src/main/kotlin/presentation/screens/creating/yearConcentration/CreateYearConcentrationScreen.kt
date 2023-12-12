package presentation.screens.creating.yearConcentration

import domain.model.YearConcentration
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import tornadofx.*
import java.time.LocalDate

class CreateYearConcentrationScreen : View() {
    private val _title = "Create Year Concentration"

    private val materialNameInputProperty = SimpleStringProperty()
    private val valueInputProperty = SimpleStringProperty()
    private val yearInputProperty = SimpleStringProperty()

    private val useCases = CreateYearConcentrationUseCases()

    private var materialNames: List<String> = listOf()

    override val root = vbox {

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
        textfield(valueInputProperty)

        button("Create") {
            action {
                if (yearInputProperty.value.toInt() in 1950..LocalDate.now().year) {
                    val concentration = YearConcentration(
                        materialName = materialNameInputProperty.value,
                        value = valueInputProperty.value.toDouble(),
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