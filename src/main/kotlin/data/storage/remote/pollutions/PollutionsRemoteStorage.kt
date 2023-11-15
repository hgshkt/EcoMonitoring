package data.storage.remote.pollutions

import data.storage.remote.pollutions.model.RemotePollution

interface PollutionsRemoteStorage {
    fun getAll(): List<RemotePollution>
}