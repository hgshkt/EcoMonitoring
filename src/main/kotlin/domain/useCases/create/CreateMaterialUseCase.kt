package domain.useCases.create

import data.repository.materials.remote.MaterialMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.model.Material
import java.sql.SQLIntegrityConstraintViolationException

class CreateMaterialUseCase(
    private val repository: MaterialsRemoteRepository = MaterialMySQLRepository(
        storage = MaterialMySQLStorage(
            connectionData = DatabaseConnectionData()
        )
    )
) {
    fun execute(material: Material, sqlException: () -> Unit) {
        try {
            repository.add(material)
        } catch (exception: SQLIntegrityConstraintViolationException) {
            sqlException()
        }
    }
}