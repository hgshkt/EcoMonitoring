package presentation.tables

import data.repository.materials.remote.MaterialMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import domain.model.Material
import domain.useCases.GetMaterialsFromRemoteRepositoryUseCase
import javafx.scene.Parent
import tornadofx.asObservable
import tornadofx.readonlyColumn
import tornadofx.tableview

class MaterialsAppTableView : AppTableView() {

    private var useCase: GetMaterialsFromRemoteRepositoryUseCase

    override val root: Parent

    init {
        useCase = GetMaterialsFromRemoteRepositoryUseCase(
            repository = MaterialMySQLRepository(
                storage = MaterialMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        )

        val materials = useCase.execute().materials

        root = tableview(materials.asObservable()) {
            readonlyColumn("Id", Material::id)
            readonlyColumn("Enterprise Id", Material::name)
            readonlyColumn("Material Id", Material::gdk)
            readonlyColumn("Year", Material::dangerClass)
        }
    }
}