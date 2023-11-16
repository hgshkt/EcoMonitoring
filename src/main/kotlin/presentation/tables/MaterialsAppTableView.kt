package presentation.tables

import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.repository.materials.remote.MaterialMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import data.storage.remote.materials.MaterialMySQLStorage
import domain.model.Material
import domain.useCases.GetEnterprisesFromRemoteRepository
import domain.useCases.GetMaterialsFromRemoteRepository
import javafx.scene.Parent
import tornadofx.asObservable
import tornadofx.readonlyColumn
import tornadofx.tableview

class MaterialsAppTableView : AppTableView() {

    private var useCase: GetMaterialsFromRemoteRepository

    override val root: Parent

    init {
        useCase = GetMaterialsFromRemoteRepository(
            repository = MaterialMySQLRepository(
                storage = MaterialMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        )

        val materials = useCase.getMaterialsFromRemoteRepository().materials

        root = tableview(materials.asObservable()) {
            readonlyColumn("Id", Material::id)
            readonlyColumn("Enterprise Id", Material::name)
            readonlyColumn("Material Id", Material::gdk)
            readonlyColumn("Year", Material::dangerClass)
        }
    }
}