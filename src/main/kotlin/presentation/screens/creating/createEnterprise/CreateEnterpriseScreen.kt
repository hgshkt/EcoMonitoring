package presentation.screens.creating.createEnterprise

import domain.model.Enterprise
import domain.useCases.create.CreateEnterpriseUseCase
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import tornadofx.*

class CreateEnterpriseScreen: View() {

    private val _title = "Create Enterprise"

    private val idInputProperty = SimpleStringProperty()
    private val nameInputProperty = SimpleStringProperty()
    private val activityInputProperty = SimpleStringProperty()
    private val belongingInputProperty = SimpleStringProperty()
    private val locationInputProperty = SimpleStringProperty()

    private val useCase = CreateEnterpriseUseCase()

    override val root = vbox {
        text("id")
        textfield(idInputProperty)

        text("name")
        textfield(nameInputProperty)

        text("activity")
        textfield(activityInputProperty)

        text("belonging")
        textfield(belongingInputProperty)

        text("location")
        textfield(locationInputProperty)

        button("Create") {
            action {
                val enterprise = Enterprise(
                    id = idInputProperty.value.toInt(),
                    name = nameInputProperty.value,
                    activity = activityInputProperty.value,
                    belonging = belongingInputProperty.value,
                    location = locationInputProperty.value
                )
                useCase.execute(enterprise) {
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