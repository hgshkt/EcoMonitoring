package presentation.screens.tables

import domain.model.TaxRate
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.Parent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import presentation.screens.tables.usecases.TableUseCases
import presentation.style.buttonSizeHeight
import presentation.style.buttonSizeWidth
import presentation.views.buttons.SelectFileButton
import tornadofx.*

class TaxRateAppTableView: AppTableView() {
    private val _title = "Tax rates"
    private var useCases: TaxRatesTableViewUseCases = TaxRatesTableViewUseCases()
    private val tableUseCases: TableUseCases = TableUseCases()

    private var selectedTableType = SimpleStringProperty(TableType.TAX_RATES.tableName)

    private var observableTaxRates: ObservableList<TaxRate>
    override val root: Parent

    init {
        val taxRates = useCases.getAll.execute()
        observableTaxRates = listOf(taxRates).toObservable()

        title = _title

        root = hbox {
            val table = tableview(observableTaxRates) {
                readonlyColumn("Material", TaxRate::materialName)
                readonlyColumn("Value", TaxRate::value)
            }

            HBox.setHgrow(table, Priority.ALWAYS)

            vbox {

                text("Tables:")

                button(TableType.ENTERPRISES.tableName) {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        replaceWith(EnterprisesAppTableView::class)
                    }
                }
                button(TableType.MATERIALS.tableName) {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        replaceWith(MaterialsAppTableView::class)
                    }
                }
                button(TableType.POLLUTION.tableName) {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        replaceWith(PollutionsAppTableView::class)
                    }
                }
                button(TableType.YEAR_CONCENTRATIONS.tableName) {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        replaceWith(YearConcentrationAppTableView::class)
                    }
                }
                button(TableType.DAMAGE_DATA.tableName) {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        replaceWith(DamageDataAppTableView::class)
                    }
                }
                button(TableType.TAX_RATES.tableName) {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                    action {
                        replaceWith(TaxRateAppTableView::class)
                    }
                }

                region {
                    prefHeight = 30.0
                }

                text("Fill data from file")

                combobox(
                    values = TableType.entries.map { it.tableName },
                    property = selectedTableType
                ) {
                    prefWidth = buttonSizeWidth
                    prefHeight = buttonSizeHeight
                }

                add(
                    child = SelectFileButton(
                        selectedTableType = selectedTableType,
                        tableUseCases = tableUseCases,
                        action = { update() }
                    )
                )

                region {
                    prefHeight = 30.0
                }

                text("Other functions")

                button("Update") {
                    action {
                        update()
                    }
                }

                button("Remove data") {
                    action {
                        useCases.delete.execute()
                        update()
                    }
                }
            }
        }
    }

    private fun update() {
        observableTaxRates.clear()
        val newData = tableUseCases.getTaxRates.execute()
        observableTaxRates.add(newData)
    }
}