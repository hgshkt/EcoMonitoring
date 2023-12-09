package domain.data.repository.yearConcentration.remote

import domain.model.YearConcentration
import domain.model.data.remote.RemoteYearConcentrationData

interface YearConcentrationsRemoteRepository {
    fun getData(): RemoteYearConcentrationData

    fun addData(data: RemoteYearConcentrationData)
    fun add(concentration: YearConcentration)

    fun delete(concentration: YearConcentration)

    fun deleteAll()
}