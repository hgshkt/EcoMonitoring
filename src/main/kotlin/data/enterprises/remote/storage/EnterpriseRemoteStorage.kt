package data.enterprises.remote.storage

import data.enterprises.remote.storage.model.RemoteStorageEnterprise

interface EnterpriseRemoteStorage {
    fun getAll(): List<RemoteStorageEnterprise>
}