package data.repository.pollutions.remote

import data.mappers.domain.pollution.toRemote
import data.mappers.remote.pollution.toDomain
import data.storage.remote.pollutions.PollutionsRemoteStorage
import domain.data.repository.pollution.remote.PollutionsRemoteRepository
import domain.model.Pollution
import domain.model.data.remote.RemotePollutionData

class PollutionMySQLRepository(
    private val storage: PollutionsRemoteStorage
) : PollutionsRemoteRepository {
    override fun getAll(): MutableList<Pollution> {
        val pollutions = storage.getAll().map {
            it.toDomain()
        }
        return pollutions.toMutableList()
    }

    override fun addData(pollutions: MutableList<Pollution>) {
        pollutions.forEach {
            storage.add(it.toRemote())
        }
    }

    override fun add(pollution: Pollution) {
        storage.add(pollution.toRemote())
    }

    override fun delete(pollution: Pollution) {
        storage.delete(pollution.toRemote())
    }

    override fun deleteAll() {
        storage.deleteAll()
    }

    override fun updateDamage(pollution: Pollution) {
        storage.updateDamage(pollution.toRemote())
    }
}