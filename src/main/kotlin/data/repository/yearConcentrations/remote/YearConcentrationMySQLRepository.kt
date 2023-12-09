package data.repository.yearConcentrations.remote

import data.mappers.domain.pollution.toRemote
import data.mappers.remote.pollution.toDomain
import domain.data.repository.yearConcentration.remote.YearConcentrationsRemoteRepository
import domain.model.YearConcentration
import domain.model.data.remote.RemoteYearConcentrationData

class YearConcentrationMySQLRepository(
    private val storage: YearConcentrationsRemoteStorage
): YearConcentrationsRemoteRepository {
    override fun getData(): RemoteYearConcentrationData {
        val concentrations = storage.getAll().map {
            it.toDomain()
        }
        return RemoteYearConcentrationData(concentrations = concentrations.toMutableList())
    }

    override fun addData(data: RemoteYearConcentrationData) {
        val concentrations = data.concentrations.map {
            it.toRemote()
        }
        storage.add(concentrations)
    }

    override fun add(concentration: YearConcentration) {
        val list = listOf(concentration.toRemote())
        storage.add(list)
    }

    override fun delete(concentration: YearConcentration) {
        storage.delete(concentration)
    }

    override fun deleteAll() {
        storage.deleteAll()
    }
}