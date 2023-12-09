package presentation.screens.creating.createPollution

import domain.model.Pollution
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import tornadofx.*
import java.time.LocalDate

class CreatePollutionScreen : View() {

    private val _title = "Create Pollution"

    private val enterpriseNameInputProperty = SimpleStringProperty()
    private val materialNameInputProperty = SimpleStringProperty()
    private val yearInputProperty = SimpleStringProperty()
    private val amountInputProperty = SimpleStringProperty()

    private val useCases = CreatePollutionScreenUseCases()

    private var enterpriseNames: List<String> = listOf()
    private var materialNames: List<String> = listOf()

    override val root = vbox {

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

        text("year")

        combobox {
            items = (1950..LocalDate.now().year).map { it.toString() }.toObservable()
            isEditable = true
            promptText = "year"

            editor.textProperty().addListener { _, _, newValue ->
                val filteredSuggestions = items.filter { year ->
                    year.toString().contains(newValue, ignoreCase = true)
                }
                items.setAll(filteredSuggestions)
            }
        }

        text("amount")
        textfield(amountInputProperty)

        button("Create") {
            action {
                val pollution = Pollution(
                    enterpriseName = enterpriseNameInputProperty.value,
                    materialName = materialNameInputProperty.value,
                    year = yearInputProperty.value.toInt(),
                    materialAmount = amountInputProperty.value.toDouble()
                )
                useCases.createUseCase.execute(pollution)
                close()
            }
        }
    }

    init {
        title = _title
    }
}