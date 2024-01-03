package data.repository.damageData.remote

import data.mappers.domain.damageData.toRemote
import data.mappers.remote.damageData.toDomain
import data.storage.remote.damageData.DamageDataRemoteStorage
import domain.data.emptyData.EmptyData.emptyDamageData
import domain.data.repository.damageData.DamageDataRemoteRepository
import domain.model.DamageData
import domain.model.Pollution

class DamageDataRemoteRepositoryImpl(
    private val storage: DamageDataRemoteStorage
) : DamageDataRemoteRepository {
    override fun put(data: DamageData) {
        storage.put(data.toRemote())
    }

    override fun get(): DamageData {
        return storage.get()?.toDomain() ?: emptyDamageData
    }

    override fun delete() {
        storage.delete()
    }
}