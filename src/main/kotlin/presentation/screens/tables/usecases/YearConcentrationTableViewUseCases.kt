package presentation.screens.tables.usecases

import data.repository.yearConcentrations.remote.YearConcentrationMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.yearConcentrations.YearConcentrationsMySQLStorage
import domain.useCases.delete.DeleteAllYearConcentrationUseCase
import domain.useCases.delete.DeleteYearConcentrationUseCase
import domain.useCases.get.GetYearConcentrationsFromRemoteRepositoryUseCase

data class YearConcentrationTableViewUseCases(
    val getYearConcentrationsFromRemoteRepositoryUseCase: GetYearConcentrationsFromRemoteRepositoryUseCase
    = GetYearConcentrationsFromRemoteRepositoryUseCase(
        repository = YearConcentrationMySQLRepository(
            storage = YearConcentrationsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val delete: DeleteYearConcentrationUseCase = DeleteYearConcentrationUseCase(
        repository = YearConcentrationMySQLRepository(
            storage = YearConcentrationsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val deleteAll: DeleteAllYearConcentrationUseCase = DeleteAllYearConcentrationUseCase(
        repository = YearConcentrationMySQLRepository(
            storage = YearConcentrationsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    )
)
