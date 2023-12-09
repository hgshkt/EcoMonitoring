package presentation.screens.creating.createMaterial

import domain.model.Material
import domain.useCases.create.CreateMaterialUseCase
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class CreateMaterialScreen: View() {

    private val _title = "Create Material"

    private val idInputProperty = SimpleStringProperty()
    private val nameInputProperty = SimpleStringProperty()
    private val gdkInputProperty = SimpleStringProperty()
    private val dangerClassInputProperty = SimpleStringProperty()
    private val RfCProperty = SimpleStringProperty()

    private val useCase = CreateMaterialUseCase()

    override val root = vbox {
        text("id")
        textfield(idInputProperty)

        text("name")
        textfield(nameInputProperty)

        text("gdk")
        textfield(gdkInputProperty)

        text("dangerClass")
        textfield(dangerClassInputProperty)

        text("RfC")
        textfield(RfCProperty)

        button("Create") {
            action {
                val material = Material(
                    id = idInputProperty.value.toInt(),
                    name = nameInputProperty.value,
                    gdk = gdkInputProperty.value.toDouble(),
                    dangerClass = dangerClassInputProperty.value.toInt(),
                    RfC = RfCProperty.value.toDouble()
                )
                useCase.execute(material)
                close()
            }
        }
    }

    init {
        title = _title
    }
}