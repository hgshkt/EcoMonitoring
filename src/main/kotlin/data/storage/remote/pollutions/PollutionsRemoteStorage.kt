package data.storage.remote.pollutions

import data.storage.remote.pollutions.model.RemotePollution
import domain.model.Pollution

interface PollutionsRemoteStorage {
    fun getAll(): List<RemotePollution>

    fun add(pollutions: List<RemotePollution>)

    fun delete(pollution: Pollution)
}