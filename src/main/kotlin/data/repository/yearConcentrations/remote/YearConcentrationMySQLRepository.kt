package data.repository.yearConcentrations.remote

import data.mappers.domain.yearConcentration.toRemote
import data.mappers.remote.yearConcentration.toDomain
import data.storage.remote.yearConcentrations.YearConcentrationsRemoteStorage
import domain.data.repository.yearConcentration.remote.YearConcentrationsRemoteRepository
import domain.model.YearConcentration
import domain.model.data.remote.RemoteYearConcentrationData


class YearConcentrationMySQLRepository(
    private val storage: YearConcentrationsRemoteStorage
) : YearConcentrationsRemoteRepository {
    override fun getData(): RemoteYearConcentrationData {
        val concentrations = storage.getAll().map {
            it.toDomain()
        }
        return RemoteYearConcentrationData(concentrations = concentrations.toMutableList())
    }

    override fun addData(data: RemoteYearConcentrationData) {
        val concentrations = data
            .concentrations
            .map { concentration ->
                val material = data.materials.find { material ->
                    material.id == concentration.materialId
                }!!
                concentration.organ = material.organ

                concentration.toRemote()
            }
        storage.add(concentrations)
    }

    override fun add(concentration: YearConcentration) {
        val list = listOf(concentration.toRemote())
        storage.add(list)
    }

    override fun deleteById(id: Int) {
        storage.deleteById(id)
    }

    override fun deleteAll() {
        storage.deleteAll()
    }
}