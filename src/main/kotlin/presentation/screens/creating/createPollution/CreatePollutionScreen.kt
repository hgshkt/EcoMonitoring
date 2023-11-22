package presentation.screens.creating.createPollution

import domain.model.Pollution
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class CreatePollutionScreen : View() {

    private val enterpriseNameInputProperty = SimpleStringProperty()
    private val materialNameInputProperty = SimpleStringProperty()
    private val yearInputProperty = SimpleStringProperty()
    private val amountInputProperty = SimpleStringProperty()

    private val useCases = CreatePollutionScreenUseCases()

    private var enterpriseNames: List<String> = listOf()
    private var materialNames: List<String> = listOf()

    override val root = vbox {

        enterpriseNames = useCases
            .getEnterprisesFromRemoteRepositoryUseCase
            .execute()
            .enterprises
            .map {
                it.name
            }

        materialNames = useCases
            .getMaterialsFromRemoteRepositoryUseCase
            .execute()
            .materials
            .map {
                it.name
            }

        text("enterpriseName")
        combobox(
            property = enterpriseNameInputProperty,
            values = enterpriseNames
        )

        text("materialName")
        combobox(
            property = materialNameInputProperty,
            values = materialNames
        )

        text("year")
        textfield(yearInputProperty)

        text("amount")
        textfield(amountInputProperty)

        button("Create") {
            action {
                val pollution = Pollution(
                    enterpriseName = enterpriseNameInputProperty.value,
                    materialName = materialNameInputProperty.value,
                    year = yearInputProperty.value.toInt(),
                    materialAmount = amountInputProperty.value.toDouble()
                )
                useCases.createUseCase.execute(pollution)
                close()
            }
        }
    }
}