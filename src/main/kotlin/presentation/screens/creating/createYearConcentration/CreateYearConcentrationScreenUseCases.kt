package presentation.screens.creating.createYearConcentration

import data.repository.materials.remote.MaterialMySQLRepository
import data.repository.yearConcentrations.remote.YearConcentrationRemoteRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.yearConcentration.YearConcentrationMySQLStorage
import domain.useCases.create.CreateYearConcentrationUseCase
import domain.useCases.get.GetMaterialsFromRemoteRepositoryUseCase

data class CreateYearConcentrationScreenUseCases(
    val getMaterialsFromRemoteRepositoryUseCase: GetMaterialsFromRemoteRepositoryUseCase
    = GetMaterialsFromRemoteRepositoryUseCase(
        repository = MaterialMySQLRepository(
            storage = MaterialMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val createYearConcentration: CreateYearConcentrationUseCase =
        CreateYearConcentrationUseCase(
            repository = YearConcentrationRemoteRepository(
                storage = YearConcentrationMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        )
)
