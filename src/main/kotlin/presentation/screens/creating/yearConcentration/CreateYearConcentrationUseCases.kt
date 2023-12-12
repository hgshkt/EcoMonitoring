package presentation.screens.creating.yearConcentration

import data.repository.materials.remote.MaterialMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import domain.useCases.create.CreateYearConcentrationUseCase
import domain.useCases.get.GetMaterialsFromRemoteRepositoryUseCase

data class CreateYearConcentrationUseCases(
    val getMaterialsFromRemoteRepositoryUseCase: GetMaterialsFromRemoteRepositoryUseCase
    = GetMaterialsFromRemoteRepositoryUseCase(
        repository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val createUseCase: CreateYearConcentrationUseCase = CreateYearConcentrationUseCase()
)