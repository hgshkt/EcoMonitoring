package presentation.screens.creating.yearConcentration

import domain.model.YearConcentration
import javafx.beans.property.SimpleStringProperty
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

        text("year")

        combobox(yearInputProperty) {
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

        text("Value")
        textfield(valueInputProperty)

        button("Create") {
            action {
                val concentration = YearConcentration(
                    materialName = "-", // TODO
                    value = valueInputProperty.value.toDouble(),
                    year = yearInputProperty.value.toInt(),
                )
                useCases.createUseCase.execute(concentration)
                close()
            }
        }
    }

    init {
        title = _title
    }
}