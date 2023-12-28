package presentation.screens.creating.createPollution

import domain.model.Pollution
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import presentation.style.creatingWindowWidth
import presentation.views.DoubleValueTextField
import tornadofx.*
import java.time.LocalDate

class CreatePollutionScreen : View() {

    private val _title = "Create Pollution"

    private val enterpriseNameInputProperty = SimpleStringProperty()
    private val materialNameInputProperty = SimpleStringProperty()
    private val yearInputProperty = SimpleStringProperty()

    private val useCases = CreatePollutionScreenUseCases()

    private var enterpriseNames: List<String> = listOf()
    private var materialNames: List<String> = listOf()

    override val root = vbox {
        prefWidth = creatingWindowWidth.toDouble()

        enterpriseNames = useCases
            .getEnterprisesFromRemoteRepositoryUseCase
            .execute()
            .enterprises
            .map {
                it.name
            }

        materialNames = useCases
            .getMaterialsFromRemoteRepositoryUseCase
            .execute()
            .materials
            .map {
                it.name
            }

        text("enterpriseName")
        combobox(
            property = enterpriseNameInputProperty,
            values = enterpriseNames
        )

        text("materialName")
        combobox(
            property = materialNameInputProperty,
            values = materialNames
        )

        text("year (1950 - ${LocalDate.now().year})")
        textfield(yearInputProperty)

        text("amount")
        val amountTextField = DoubleValueTextField()
        add(amountTextField)

        text("concentration")
        val concentrationTextField = DoubleValueTextField()
        add(concentrationTextField)

        button("Create") {
            action {
                if (yearInputProperty.value.toInt() in 1950..LocalDate.now().year) {
                    val pollution = Pollution(
                        enterpriseName = enterpriseNameInputProperty.value,
                        materialName = materialNameInputProperty.value,
                        year = yearInputProperty.value.toInt(),
                        materialAmount = concentrationTextField.text.toDouble(),
                        concentration = concentrationTextField.text.toDouble()
                    )
                    useCases.createUseCase.execute(pollution) {
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