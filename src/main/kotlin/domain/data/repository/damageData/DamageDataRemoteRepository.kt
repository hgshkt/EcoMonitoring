package domain.data.repository.damageData

import domain.model.DamageData

interface DamageDataRemoteRepository {

    fun put(data: DamageData)

    fun get(): DamageData
}