package presentation.screens.creating.createMaterial

import domain.model.Material
import domain.useCases.create.CreateMaterialUseCase
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import presentation.style.creatingWindowWidth
import tornadofx.*

class CreateMaterialScreen : View() {

    private val _title = "Create Material"

    private val idInputProperty = SimpleStringProperty()
    private val nameInputProperty = SimpleStringProperty()
    private val gdkInputProperty = SimpleStringProperty()
    private val dangerClassInputProperty = SimpleIntegerProperty().also { it.value = 1 }
    private val RfCProperty = SimpleStringProperty()
    private val organProperty = SimpleStringProperty().also { it.value = organs.first() }

    private val useCase = CreateMaterialUseCase()

    override val root = vbox {
        prefWidth = creatingWindowWidth.toDouble()

        text("№")
        textfield(idInputProperty)

        text("name")
        textfield(nameInputProperty)

        text("gdk")
        textfield(gdkInputProperty)

        text("dangerClass")
        combobox(
            property = dangerClassInputProperty,
            values = listOf(1, 2, 3, 4)
        )

        text("RfC")
        textfield(RfCProperty)

        text("Organ")
        combobox(
            property = organProperty,
            values = organs
        )

        button("Create") {
            action {
                val material = Material(
                    id = idInputProperty.value.toInt(),
                    name = nameInputProperty.value,
                    gdk = gdkInputProperty.value.toDouble(),
                    dangerClass = dangerClassInputProperty.value,
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

    companion object {
        val organs = listOf(
            "Нирки",
            "Печінка",
            "Органи дихання",
            "М'язова система",
            "ЦНС",
            "Нервова система",
            "Розвиток",
            "ШКТ",
            "Репродукція",
            "Гормональна система",
            "Кісткова система",
            "Волосся",
            "Імунитет",
            "Серцево-судинна система",
            "Кров",
            "Маса тіла"
        )
    }
}