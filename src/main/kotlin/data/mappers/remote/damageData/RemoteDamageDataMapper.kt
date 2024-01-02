package data.mappers.remote.damageData

import data.storage.remote.damageData.model.RemoteDamageData
import domain.model.DamageData

fun RemoteDamageData.toDomain(): DamageData {
    return DamageData(
        population = population,
        knas = knas,
        kf = kf,
        kt = kt,
        P = P
    )
}