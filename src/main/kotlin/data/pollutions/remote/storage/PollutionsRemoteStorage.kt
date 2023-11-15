package data.pollutions.remote.storage

import data.pollutions.remote.storage.model.RemoteStoragePollution

interface PollutionsRemoteStorage {
    fun getData(): List<RemoteStoragePollution>
}