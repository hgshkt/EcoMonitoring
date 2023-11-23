package presentation.screens.tables.usecases

import data.repository.pollutions.remote.PollutionMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.pollutions.PollutionsMySQLStorage
import domain.useCases.delete.DeletePollutionUseCase
import domain.useCases.get.GetPollutionsFromRemoteRepositoryUseCase

data class PollutionTableViewUseCases(
    val getPollutionsFromRemoteRepositoryUseCase: GetPollutionsFromRemoteRepositoryUseCase
    = GetPollutionsFromRemoteRepositoryUseCase(
        repository = PollutionMySQLRepository(
            storage = PollutionsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    ),
    val delete: DeletePollutionUseCase = DeletePollutionUseCase(
        repository = PollutionMySQLRepository(
            storage = PollutionsMySQLStorage(
                connectionData = DatabaseConnectionData()
            )
        )
    )
)
