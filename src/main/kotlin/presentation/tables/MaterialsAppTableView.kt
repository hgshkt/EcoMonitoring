package presentation.tables

import domain.model.Material

import tornadofx.*

class MaterialsAppTableView(
    private val materials: MutableList<Material>
) : AppTableView() {

    override val root = vbox {
        tableview(materials.asObservable()) {
            readonlyColumn("Id", Material::id)
            readonlyColumn("Enterprise Id", Material::name)
            readonlyColumn("Material Id", Material::gdk)
            readonlyColumn("Year", Material::dangerClass)
        }
    }
}