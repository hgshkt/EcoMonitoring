package presentation.screens.tables.usecases

import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.repository.materials.remote.MaterialMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import data.storage.remote.materials.MaterialMySQLStorage
import domain.useCases.delete.DeleteAllEnterpriseUseCase
import domain.useCases.delete.DeleteAllMaterialUseCase
import domain.useCases.delete.DeleteMaterialByIdUseCase
import domain.useCases.get.GetMaterialsFromRemoteRepositoryUseCase

data class MaterialTableViewUseCases(
    val getMaterialsFromRemoteRepositoryUseCase: GetMaterialsFromRemoteRepositoryUseCase
    = GetMaterialsFromRemoteRepositoryUseCase(
        repository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val delete: DeleteMaterialByIdUseCase = DeleteMaterialByIdUseCase(
        repository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val deleteAll: DeleteAllMaterialUseCase = DeleteAllMaterialUseCase(
        repository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    )
)
