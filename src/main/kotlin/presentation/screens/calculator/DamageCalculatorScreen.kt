package presentation.screens.calculator

import com.sun.javafx.scene.control.IntegerField
import domain.model.Material
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import presentation.views.textField.DoubleValueTextField
import tornadofx.*

class DamageCalculatorScreen : View() {

    private val miProperty = SimpleDoubleProperty()
    private val PProperty = SimpleIntegerProperty()
    private val AiProperty = SimpleDoubleProperty()
    private val KtProperty = SimpleDoubleProperty()
    private val KziProperty = SimpleDoubleProperty()

    private val result = SimpleDoubleProperty()

    private var materials: List<Material> = listOf()
    private val materialNameInputProperty = SimpleStringProperty()

    private val calculatorScreenUseCases = CalculatorUseCases()

    override val root = vbox {
        materials = calculatorScreenUseCases.getMaterials.execute().materials

        text(
            "Розрахунок   маси  наднормативного  викиду  забруднюючої \n" +
                    "речовини в атмосферне  повітря  від  джерела  викиду  забруднюючих \n" +
                    "речовин"
        )

        text("Матеріал")
        combobox(
            property = materialNameInputProperty,
            values = materials.map { it.name }
        )

        text("Середнє значення масової концентрації")
        val concentrationField = DoubleValueTextField()
        add(concentrationField)

        text("Значення  об'ємної  витрати  газопилового  потоку, куб.м/с")
        val qvField = DoubleValueTextField()
        add(qvField)

        text("Час  роботи джерела викиду в режимі наднормативного викиду, год.")
        val TField = IntegerField()
        add(TField)

        button("Розрахунок") {
            action {
                miProperty.value = calculatorScreenUseCases.calcMass.execute(
                    material = materials.find {
                        materialNameInputProperty.value == it.name
                    }!!,
                    concentration = concentrationField.text.toDouble(),
                    qv = qvField.text.toDouble(),
                    T = TField.value
                )
            }
        }

        text("маса:")
        text(miProperty.value.toString())
    }

    init {

    }
}