package data.storage.remote.yearConcentrations

import data.storage.remote.yearConcentrations.model.RemoteYearConcentration

interface YearConcentrationsRemoteStorage {
    fun getAll(): List<RemoteYearConcentration>

    fun getById(id: Int): RemoteYearConcentration?

    fun add(concentration: RemoteYearConcentration)

    fun deleteById(id: Int)

    fun deleteAll()
}