package data.repository.dayConcentrations.remote

import data.mappers.domain.dayConcentration.toRemote
import data.mappers.remote.dayConcentration.toDomain
import data.storage.remote.dayConcentrations.DayConcentrationsRemoteStorage
import domain.data.repository.dayConcentration.remote.DayConcentrationsRemoteRepository
import domain.model.Material
import domain.model.DayConcentration
import domain.model.data.remote.RemoteDayConcentrationData


class DayConcentrationMySQLRepository(
    private val storage: DayConcentrationsRemoteStorage
) : DayConcentrationsRemoteRepository {
    override fun getData(): RemoteDayConcentrationData {
        val concentrations = storage.getAll().map {
            it.toDomain()
        }
        return RemoteDayConcentrationData(concentrations = concentrations.toMutableList())
    }

    override fun addData(data: RemoteDayConcentrationData) {
        data.concentrations
            .map { concentration ->
                concentration.toRemote()
            }.forEach { concentration ->

                val material = data.materials.find { material ->
                    material.id == concentration.materialId
                }!!

                concentration.organ = material.organ

                storage.add(concentration)
            }
    }

    override fun add(concentration: DayConcentration, material: Material) {
        concentration.organ = material.organ
        storage.add(concentration.toRemote())
    }

    override fun deleteById(id: Int) {
        storage.deleteById(id)
    }

    override fun deleteAll() {
        storage.deleteAll()
    }
}