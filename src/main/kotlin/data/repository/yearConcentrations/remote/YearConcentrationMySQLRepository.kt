package data.repository.yearConcentrations.remote

import data.mappers.domain.yearConcentration.toRemote
import data.mappers.remote.yearConcentration.toDomain
import data.storage.remote.materials.MaterialRemoteStorage
import data.storage.remote.yearConcentrations.YearConcentrationsRemoteStorage
import domain.data.obtained.calculators.RiskCalculator
import domain.data.repository.material.remote.MaterialsRemoteRepository
import domain.data.repository.yearConcentration.remote.YearConcentrationsRemoteRepository
import domain.model.Material
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

    override fun add(concentration: YearConcentration, material: Material) {
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