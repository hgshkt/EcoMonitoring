package presentation.screens.creating.createPollution

import data.repository.enterprises.remote.EnterpriseMySQLRepository
import data.repository.materials.remote.MaterialMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import data.storage.remote.materials.MaterialMySQLStorage
import domain.useCases.create.CreatePollutionUseCase
import domain.useCases.get.GetEnterprisesFromRemoteRepositoryUseCase
import domain.useCases.get.GetMaterialsFromRemoteRepositoryUseCase

data class CreatePollutionScreenUseCases(
    val getEnterprisesFromRemoteRepositoryUseCase: GetEnterprisesFromRemoteRepositoryUseCase
    = GetEnterprisesFromRemoteRepositoryUseCase(
        repository = EnterpriseMySQLRepository(
            storage = EnterprisesMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val getMaterialsFromRemoteRepositoryUseCase: GetMaterialsFromRemoteRepositoryUseCase
    = GetMaterialsFromRemoteRepositoryUseCase(
        repository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val createUseCase: CreatePollutionUseCase = CreatePollutionUseCase()
)
