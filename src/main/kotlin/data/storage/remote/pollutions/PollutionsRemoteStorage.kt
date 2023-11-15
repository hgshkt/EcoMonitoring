package data.storage.remote.pollutions

import data.storage.remote.pollutions.model.RemoteStoragePollution

interface PollutionsRemoteStorage {
    fun getAll(): List<RemoteStoragePollution>
}