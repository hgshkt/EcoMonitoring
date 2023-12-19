package presentation.views

import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import javafx.scene.control.TextField

class DoubleValueTextField() : TextField("0.0") {

    private val defaultValue = "0.0"

    init {
        focusedProperty().addListener { arg0: ObservableValue<out Boolean?>?, oldValue: Boolean?, newValue: Boolean? ->
            if (!newValue!!) {
                if (!text.matches(Regex("^\\d*\\.?\\d+\$"))) {
                    text = defaultValue
                }
            }
        }
    }
}