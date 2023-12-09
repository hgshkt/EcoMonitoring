package data.storage.remote.yearConcentrations

import data.storage.remote.yearConcentrations.model.RemoteYearConcentration
import domain.model.YearConcentration

interface YearConcentrationsRemoteStorage {
    fun getAll(): List<RemoteYearConcentration>

    fun add(concentrations: List<RemoteYearConcentration>)

    fun delete(concentration: YearConcentration)

    fun deleteAll()
}