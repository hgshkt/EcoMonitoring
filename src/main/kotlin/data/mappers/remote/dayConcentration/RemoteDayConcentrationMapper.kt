package data.mappers.remote.dayConcentration

import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.dayConcentrations.model.RemoteDayConcentration
import domain.model.DayConcentration

fun RemoteDayConcentration.toDomain(): DayConcentration {

    val materialStorage = MaterialMySQLStorage(DatabaseConnectionData())

    val materials = materialStorage.getAll()

    return DayConcentration(
        id = id,
        materialName = materials.find { it.id == materialId }!!.name,
        value = value,
        year = year,
        carcinogenicRisk = carcinogenicRisk,
        nonCarcinogenicRisk = nonCarcinogenicRisk,
        organ = organ,
        carcinogenicRiskLevel = carcinogenicRiskLevel,
        nonCarcinogenicRiskLevel = nonCarcinogenicRiskLevel
    )
}