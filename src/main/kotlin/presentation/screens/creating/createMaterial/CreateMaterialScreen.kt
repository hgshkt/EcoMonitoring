package presentation.screens.creating.createMaterial

import domain.model.Material
import domain.useCases.create.CreateMaterialUseCase
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import tornadofx.*

class CreateMaterialScreen : View() {

    private val _title = "Create Material"

    private val idInputProperty = SimpleStringProperty()
    private val nameInputProperty = SimpleStringProperty()
    private val gdkInputProperty = SimpleStringProperty()
    private val dangerClassInputProperty = SimpleStringProperty()
    private val RfCProperty = SimpleStringProperty()
    private val organProperty = SimpleStringProperty()

    private val useCase = CreateMaterialUseCase()

    override val root = vbox {
        text("№")
        textfield(idInputProperty)

        text("name")
        textfield(nameInputProperty)

        text("gdk")
        textfield(gdkInputProperty)

        text("dangerClass")
        textfield(dangerClassInputProperty)

        text("RfC")
        textfield(RfCProperty)

        text("Organ")
        textfield(organProperty)

        button("Create") {
            action {
                val material = Material(
                    id = idInputProperty.value.toInt(),
                    name = nameInputProperty.value,
                    gdk = gdkInputProperty.value.toDouble(),
                    dangerClass = dangerClassInputProperty.value.toInt(),
                    RfC = RfCProperty.value.toDouble(),
                    organ = organProperty.value
                )
                useCase.execute(material) {
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