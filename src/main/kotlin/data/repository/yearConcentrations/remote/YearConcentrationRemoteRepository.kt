package data.repository.yearConcentrations.remote

import data.mappers.domain.yearConcentration.toRemote
import data.mappers.remote.yearConcentration.toDomain
import data.storage.remote.yearConcentration.YearConcentrationRemoteStorage
import domain.data.repository.yearConcentration.remote.YearConcentrationRemoteRepository
import domain.model.YearConcentration

class YearConcentrationRemoteRepository(
    private val storage: YearConcentrationRemoteStorage
): YearConcentrationRemoteRepository {
    override fun getAll(): MutableList<YearConcentration> {
        return storage.getAll().map { it.toDomain() }.toMutableList()
    }

    override fun add(concentration: YearConcentration) {
        storage.add(concentration.toRemote())
    }

    override fun update(concentration: YearConcentration) {
        storage.update(concentration.toRemote())
    }

    override fun deleteById(id: Int) {
        storage.deleteById(id)
    }

    override fun deleteAll() {
        storage.deleteAll()
    }
}