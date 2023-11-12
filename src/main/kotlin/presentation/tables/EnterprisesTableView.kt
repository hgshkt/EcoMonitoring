package presentation.tables

import tornadofx.*

class EnterprisesTableView : View() {
    override val root = hbox {
        label("enterprises")
        button("Go to materials screen") {
            action {
                replaceWith<MaterialsTableView>()
            }
        }
    }
}