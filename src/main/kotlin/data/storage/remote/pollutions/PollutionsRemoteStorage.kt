package data.storage.remote.pollutions

import data.storage.remote.pollutions.model.RemotePollution

interface PollutionsRemoteStorage {
    fun getAll(): List<RemotePollution>

    fun add(pollutions: List<RemotePollution>)
}