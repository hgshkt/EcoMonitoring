package domain.data.repository.yearConcentration.remote

import domain.model.Material
import domain.model.YearConcentration
import domain.model.data.remote.RemoteYearConcentrationData

interface YearConcentrationsRemoteRepository {
    fun getData(): RemoteYearConcentrationData

    fun addData(data: RemoteYearConcentrationData)
    fun add(concentration: YearConcentration, material: Material)

    fun deleteById(id: Int)

    fun deleteAll()
}