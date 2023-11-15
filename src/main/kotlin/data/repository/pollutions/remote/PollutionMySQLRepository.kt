package data.repository.pollutions.remote

import data.storage.remote.pollutions.PollutionsRemoteStorage
import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.data.remote.RemotePollutionData

class PollutionMySQLRepository(
    private val storage: PollutionsRemoteStorage
): PollutionsRemoteRepository {
    override fun getData(): RemotePollutionData {
        val pollutions = storage.getAll().map { it.toDomain() }
        return RemotePollutionData(pollutions = pollutions)
    }
}