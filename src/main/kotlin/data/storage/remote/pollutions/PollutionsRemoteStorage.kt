package data.storage.remote.pollutions

import data.storage.remote.pollutions.model.RemoteStoragePollution

interface PollutionsRemoteStorage {
    fun getData(): List<RemoteStoragePollution>
}