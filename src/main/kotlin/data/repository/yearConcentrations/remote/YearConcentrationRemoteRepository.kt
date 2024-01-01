package data.repository.yearConcentrations.remote

import data.storage.remote.yearConcentration.YearConcentrationMySQLStorage
import domain.data.repository.yearConcentration.remote.YearConcentrationRemoteRepository
import domain.model.YearConcentration

class YearConcentrationRemoteRepository(
    private val storage: YearConcentrationMySQLStorage
): YearConcentrationRemoteRepository {
    override fun getAll(): MutableList<YearConcentration> {
        return storage.getAll().map { it.toDomain() }
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