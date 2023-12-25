package data.storage.remote.dayConcentrations

import data.storage.remote.dayConcentrations.model.RemoteDayConcentration

interface DayConcentrationsRemoteStorage {
    fun getAll(): List<RemoteDayConcentration>

    fun getById(id: Int): RemoteDayConcentration?

    fun add(concentration: RemoteDayConcentration)

    fun deleteById(id: Int)

    fun deleteAll()
}