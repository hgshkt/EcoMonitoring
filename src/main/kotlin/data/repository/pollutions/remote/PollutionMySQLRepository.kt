package data.repository.pollutions.remote

import data.mappers.remoteDataToDomain.MapperRemoteToDomainPollution
import data.mappers.remoteDataToDomain.toDomain
import data.storage.remote.pollutions.PollutionsRemoteStorage
import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.data.remote.RemotePollutionData

class PollutionMySQLRepository(
    private val storage: PollutionsRemoteStorage,
    private val mapper: MapperRemoteToDomainPollution
): PollutionsRemoteRepository {
    override fun getData(): RemotePollutionData {
        val pollutions = storage.getAll().map { it.toDomain(mapper) }
        return RemotePollutionData(pollutions = pollutions.toMutableList())
    }
}