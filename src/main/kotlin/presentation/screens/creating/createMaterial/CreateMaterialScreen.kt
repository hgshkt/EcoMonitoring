package presentation.screens.creating.createMaterial

import domain.model.Material
import domain.useCases.create.CreateMaterialUseCase
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class CreateMaterialScreen: View() {

    private val idInputProperty = SimpleStringProperty()
    private val nameInputProperty = SimpleStringProperty()
    private val gdkInputProperty = SimpleStringProperty()
    private val dangerClassInputProperty = SimpleStringProperty()

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

        button("Create") {
            action {
                val material = Material(
                    id = idInputProperty.value.toInt(),
                    name = nameInputProperty.value,
                    gdk = gdkInputProperty.value.toDouble(),
                    dangerClass = dangerClassInputProperty.value.toInt()
                )
                useCase.execute(material)
                close()
            }
        }
    }
}