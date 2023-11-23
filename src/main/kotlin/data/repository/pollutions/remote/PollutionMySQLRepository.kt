package data.repository.pollutions.remote

import data.mappers.domain.pollution.toRemote
import data.mappers.remote.pollution.toDomain
import data.storage.remote.pollutions.PollutionsRemoteStorage
import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.Pollution
import domain.model.data.remote.RemotePollutionData

class PollutionMySQLRepository(
    private val storage: PollutionsRemoteStorage
): PollutionsRemoteRepository {
    override fun getData(): RemotePollutionData {
        val pollutions = storage.getAll().map {
            it.toDomain()
        }
        return RemotePollutionData(pollutions = pollutions.toMutableList())
    }

    override fun addData(data: RemotePollutionData) {
        val pollutions = data.pollutions.map {
            it.toRemote()
        }
        storage.add(pollutions)
    }

    override fun add(pollution: Pollution) {
        val list = listOf(pollution.toRemote())
        storage.add(list)
    }

    override fun delete(pollution: Pollution) {
        storage.delete(pollution)
    }
}