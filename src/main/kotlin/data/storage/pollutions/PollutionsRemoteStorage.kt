package data.storage.pollutions

import data.storage.pollutions.model.RemoteStoragePollution

interface PollutionsRemoteStorage {
    fun getData(): List<RemoteStoragePollution>
}