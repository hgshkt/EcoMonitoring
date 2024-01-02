package data.mappers.domain.damageData

import data.storage.remote.damageData.model.RemoteDamageData
import domain.model.DamageData

fun DamageData.toRemote(): RemoteDamageData {
    return RemoteDamageData(
        population = population,
        knas = knas,
        kf = kf,
        kt = kt,
        P = P
    )
}