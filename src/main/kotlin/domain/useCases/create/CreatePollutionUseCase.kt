package domain.useCases.create

import data.repository.pollutions.remote.PollutionMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.pollutions.PollutionsMySQLStorage
import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.Pollution
import java.sql.SQLException
import java.sql.SQLIntegrityConstraintViolationException

class CreatePollutionUseCase(
    private val repository: PollutionsRemoteRepository = PollutionMySQLRepository(
        storage = PollutionsMySQLStorage(
            connectionData = DatabaseConnectionData()
        )
    )
) {
    fun execute(pollution: Pollution, sqlException: () -> Unit) {
        try {
            repository.add(pollution)
        } catch (exception: SQLIntegrityConstraintViolationException) {
            sqlException()
        }
    }
}