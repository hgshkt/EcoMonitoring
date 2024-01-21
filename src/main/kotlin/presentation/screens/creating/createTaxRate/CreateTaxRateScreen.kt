package presentation.screens.creating.createTaxRate

import domain.model.TaxRate
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import presentation.style.creatingWindowWidth
import tornadofx.*

class CreateTaxRateScreen : View() {

    private val _title = "Create Tax Rate"

    private val materialNameInputProperty = SimpleStringProperty()
    private val valueInputProperty = SimpleStringProperty()

    private val useCases = CreateTaxRateScreenUseCases()

    private var materialNames: List<String> = listOf()

    override val root = vbox {
        prefWidth = creatingWindowWidth.toDouble()

        materialNames = useCases
            .getMaterials
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

        text("Value")
        textfield(valueInputProperty)

        button("Create") {
            action {

                val taxRate = TaxRate(
                    id = -1,
                    materialName = materialNameInputProperty.value,
                    value = valueInputProperty.value.toDouble()
                )
                useCases.create.execute(taxRate) {
                    sqlException()
                }
                close()
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