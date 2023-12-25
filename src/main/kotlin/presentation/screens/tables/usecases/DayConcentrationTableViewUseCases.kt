package presentation.screens.tables.usecases

import data.repository.dayConcentrations.remote.DayConcentrationMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.dayConcentrations.DayConcentrationsMySQLStorage
import domain.useCases.delete.DeleteAllDayConcentrationUseCase
import domain.useCases.delete.DeleteDayConcentrationUseCase
import domain.useCases.get.GetDayConcentrationsFromRemoteRepositoryUseCase

data class DayConcentrationTableViewUseCases(
    val getDayConcentrationsFromRemoteRepositoryUseCase: GetDayConcentrationsFromRemoteRepositoryUseCase
    = GetDayConcentrationsFromRemoteRepositoryUseCase(
        repository = DayConcentrationMySQLRepository(
            storage = DayConcentrationsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val delete: DeleteDayConcentrationUseCase = DeleteDayConcentrationUseCase(
        repository = DayConcentrationMySQLRepository(
            storage = DayConcentrationsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val deleteAll: DeleteAllDayConcentrationUseCase = DeleteAllDayConcentrationUseCase(
        repository = DayConcentrationMySQLRepository(
            storage = DayConcentrationsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    )
)
