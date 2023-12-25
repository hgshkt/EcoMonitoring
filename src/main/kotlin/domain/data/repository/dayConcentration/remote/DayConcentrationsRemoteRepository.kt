package domain.data.repository.dayConcentration.remote

import domain.model.Material
import domain.model.DayConcentration
import domain.model.data.remote.RemoteDayConcentrationData

interface DayConcentrationsRemoteRepository {
    fun getData(): RemoteDayConcentrationData

    fun addData(data: RemoteDayConcentrationData)
    fun add(concentration: DayConcentration, material: Material)

    fun deleteById(id: Int)

    fun deleteAll()
}