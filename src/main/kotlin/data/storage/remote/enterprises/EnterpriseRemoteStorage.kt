package data.storage.remote.enterprises

import data.storage.remote.enterprises.model.RemoteStorageEnterprise

interface EnterpriseRemoteStorage {
    fun getAll(): List<RemoteStorageEnterprise>
}