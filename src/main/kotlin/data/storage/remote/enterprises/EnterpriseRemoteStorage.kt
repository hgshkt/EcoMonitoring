package data.storage.remote.enterprises

import data.storage.remote.enterprises.model.RemoteEnterprise

interface EnterpriseRemoteStorage {
    fun getAll(): List<RemoteEnterprise>

    fun getById(id: Int): RemoteEnterprise?

    fun add(enterprises: List<RemoteEnterprise>)
}