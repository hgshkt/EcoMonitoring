package presentation.screens.tables

import domain.data.emptyData.EmptyData.emptyDamageData
import domain.model.DamageData
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.Parent
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import presentation.screens.tables.usecases.DamageDataTableViewUseCases
import presentation.screens.tables.usecases.TableUseCases
import presentation.style.buttonSizeHeight
import presentation.style.buttonSizeWidth
import presentation.views.buttons.SelectFileButton
import tornadofx.*

class DamageDataAppTableView : AppTableView() {

    private val _title = "Damage data"
    private var useCases: DamageDataTableViewUseCases = DamageDataTableViewUseCases()
    private val tableUseCases: TableUseCases = TableUseCases()

    private var selectedTableType = SimpleStringProperty(TableType.DAMAGE_DATA.tableName)

    private var observableDamageData: ObservableList<DamageData>

    override val root: Parent

    init {
        val damageData = useCases.get.execute()
        observableDamageData = listOf(damageData).toObservable()

        title = _title

        root = hbox {
            val table = tableview(observableDamageData) {
                readonlyColumn("Population", DamageData::population).cellFormat {
                    graphic = hbox {
                        text(if (it == emptyDamageData.population) "" else it.toString())
                    }
                }
                readonlyColumn("Minimum salary", DamageData::P).cellFormat {
                    graphic = hbox {
                        text(if (it == emptyDamageData.P) "" else it.toString())
                    }
                }
                readonlyColumn("Kf", DamageData::kf).cellFormat {
                    graphic = hbox {
                        text(if (it == emptyDamageData.kf) "" else it.toString())
                    }
                }
                readonlyColumn("Knas", DamageData::knas).cellFormat {
                    graphic = hbox {
                        text(if (it == emptyDamageData.knas) "" else it.toString())
                    }
                }
                readonlyColumn("Kt", DamageData::kt).cellFormat {
                    graphic = hbox {
                        text(if (it == emptyDamageData.kt) "" else it.toString())
                    }
                }
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
        observableDamageData.clear()
        val newData = tableUseCases.getDamageData.execute()
        observableDamageData.add(newData)
    }
}