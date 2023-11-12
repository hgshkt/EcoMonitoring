package presentation.tables

import tornadofx.*

class MaterialsTableView : View() {
    override val root = hbox {
        label("materials")
        button("Go to enterprises screen") {
            action {
                replaceWith<EnterprisesTableView>()
            }
        }
    }
}