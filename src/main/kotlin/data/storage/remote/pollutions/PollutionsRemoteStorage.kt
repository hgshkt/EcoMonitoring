package data.storage.remote.pollutions

import data.storage.remote.pollutions.model.RemotePollution
import domain.model.Pollution
import java.rmi.Remote

interface PollutionsRemoteStorage {
    fun getAll(): List<RemotePollution>

    fun add(pollution: RemotePollution)

    fun delete(pollution: RemotePollution)

    fun deleteAll()

    fun updateDamage(pollution: RemotePollution)

    fun updateTax(pollution: RemotePollution)
}