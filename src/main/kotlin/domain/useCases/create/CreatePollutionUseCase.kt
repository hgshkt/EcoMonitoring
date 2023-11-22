package domain.useCases.create

import data.repository.pollutions.remote.PollutionMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.pollutions.PollutionsMySQLStorage
import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.Pollution

class CreatePollutionUseCase(
    private val repository: PollutionsRemoteRepository = PollutionMySQLRepository(
        storage = PollutionsMySQLStorage(
            connectionData = DatabaseConnectionData()
        )
    )
) {
    fun execute(pollution: Pollution) {
        repository.add(pollution)
    }
}