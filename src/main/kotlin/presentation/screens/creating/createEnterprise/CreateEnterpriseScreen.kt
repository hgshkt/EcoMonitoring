package presentation.screens.creating.createEnterprise

import domain.model.Enterprise
import domain.useCases.create.CreateEnterpriseUseCase
import domain.useCases.update.UpdateEnterpriseUseCase
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import presentation.controllers.CreateEnterpriseController
import presentation.style.creatingWindowWidth
import tornadofx.*

class CreateEnterpriseScreen : View() {

    private val controller: CreateEnterpriseController by inject()

    private val _title = "Create Enterprise"

    private val nameInputProperty = SimpleStringProperty(controller.name)
    private val activityInputProperty = SimpleStringProperty(controller.activity)
    private val belongingInputProperty = SimpleStringProperty(controller.belonging)
    private val locationInputProperty = SimpleStringProperty(controller.location)

    private val create = CreateEnterpriseUseCase()
    private val update = UpdateEnterpriseUseCase()

    override val root = vbox {
        prefWidth = creatingWindowWidth.toDouble()

        text("name")
        textfield(nameInputProperty)

        text("activity")
        textfield(activityInputProperty)

        text("belonging")
        textfield(belongingInputProperty)

        text("location")
        textfield(locationInputProperty)

        button(if (controller.edit) "Edit" else "Create") {
            action {
                val enterprise = Enterprise(
                    id = controller.id,
                    name = nameInputProperty.value,
                    activity = activityInputProperty.value,
                    belonging = belongingInputProperty.value,
                    location = locationInputProperty.value
                )
                if (controller.edit)
                    update.execute(enterprise)
                else
                    create.execute(enterprise) {
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