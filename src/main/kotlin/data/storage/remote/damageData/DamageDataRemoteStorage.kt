package data.storage.remote.damageData

import data.storage.remote.damageData.model.RemoteDamageData

interface DamageDataRemoteStorage {
    fun put(data: RemoteDamageData)

    fun get(): RemoteDamageData?

    fun delete()
}