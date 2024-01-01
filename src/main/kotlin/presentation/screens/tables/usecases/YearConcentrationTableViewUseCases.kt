package presentation.screens.tables.usecases

import data.repository.yearConcentrations.remote.YearConcentrationRemoteRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.yearConcentration.YearConcentrationMySQLStorage
import domain.useCases.delete.DeleteAllYearConcentrationUseCase
import domain.useCases.delete.DeleteYearConcentrationByIdUseCase
import domain.useCases.get.GetYearConcentrationsFromRemoteRepositoryUseCase

data class YearConcentrationTableViewUseCases(
    val getAll: GetYearConcentrationsFromRemoteRepositoryUseCase =
        GetYearConcentrationsFromRemoteRepositoryUseCase(
            repository = YearConcentrationRemoteRepository(
                storage = YearConcentrationMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        ),
    val delete: DeleteYearConcentrationByIdUseCase =
        DeleteYearConcentrationByIdUseCase(
            repository = YearConcentrationRemoteRepository(
                storage = YearConcentrationMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        ),
    val deleteAll: DeleteAllYearConcentrationUseCase =
        DeleteAllYearConcentrationUseCase(
            repository = YearConcentrationRemoteRepository(
                storage = YearConcentrationMySQLStorage(
                    connectionData = DatabaseConnectionData()
                )
            )
        )
)
