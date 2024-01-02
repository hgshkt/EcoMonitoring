package data.mappers.excel.damageData

import data.storage.excel.damageData.model.ExcelDamageData
import domain.model.DamageData

fun ExcelDamageData.toDomain(): DamageData {
    return DamageData(
        population = population,
        knas = 0.0,
        kf = kf,
        kt = 0.0,
        P = P
    )
}