package data.storage.remote.enterprises

import data.storage.remote.enterprises.model.RemoteEnterprise

interface EnterpriseRemoteStorage {
    fun getAll(): List<RemoteEnterprise>
}