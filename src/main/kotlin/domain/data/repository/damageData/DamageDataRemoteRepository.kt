package domain.data.repository.damageData

import domain.model.DamageData
import domain.model.Pollution

interface DamageDataRemoteRepository {

    fun put(data: DamageData)

    fun get(): DamageData

    fun delete()
}