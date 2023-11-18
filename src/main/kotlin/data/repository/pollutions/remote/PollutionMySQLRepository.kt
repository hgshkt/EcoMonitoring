package data.repository.pollutions.remote

import data.mappers.domainDataToRemote.MapperDomainPollutionToRemote
import data.mappers.remoteDataToDomain.MapperRemoteToDomainPollution
import data.mappers.remoteDataToDomain.toDomain
import data.storage.remote.pollutions.PollutionsRemoteStorage
import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.data.remote.RemotePollutionData

class PollutionMySQLRepository(
    private val storage: PollutionsRemoteStorage,
    private val mapperRemoteToDomain: MapperRemoteToDomainPollution,
    private val mapperDomainToRemote: MapperDomainPollutionToRemote
): PollutionsRemoteRepository {
    override fun getData(): RemotePollutionData {
        val pollutions = storage.getAll().map { it.toDomain(mapperRemoteToDomain) }
        return RemotePollutionData(pollutions = pollutions.toMutableList())
    }

    override fun addData(data: RemotePollutionData) {
        val pollutions = data.pollutions.map {
            mapperDomainToRemote.execute(it)
        }
        storage.add(pollutions)
    }
}