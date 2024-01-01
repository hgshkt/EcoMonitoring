package data.storage.remote.yearConcentration

import data.storage.remote.yearConcentration.model.RemoteYearConcentration

interface YearConcentrationRemoteStorage {
    fun getAll(): List<RemoteYearConcentration>

    fun add(concentration: RemoteYearConcentration)

    fun update(concentration: RemoteYearConcentration)

    fun deleteById(id: Int)

    fun deleteAll()
}