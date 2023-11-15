package data.storage.enterprises

import data.storage.enterprises.model.RemoteStorageEnterprise

interface EnterpriseRemoteStorage {
    fun getAll(): List<RemoteStorageEnterprise>
}