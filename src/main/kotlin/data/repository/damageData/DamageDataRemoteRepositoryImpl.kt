package data.repository.damageData

import data.mappers.domain.damageData.toRemote
import data.mappers.remote.damageData.toDomain
import data.storage.remote.damageData.DamageDataRemoteStorage
import domain.data.repository.damageData.DamageDataRemoteRepository
import domain.model.DamageData

class DamageDataRemoteRepositoryImpl(
    private val storage: DamageDataRemoteStorage
) : DamageDataRemoteRepository {
    override fun put(data: DamageData) {
        storage.put(data.toRemote())
    }

    override fun get(): DamageData {
        return storage.get().toDomain()
    }
}