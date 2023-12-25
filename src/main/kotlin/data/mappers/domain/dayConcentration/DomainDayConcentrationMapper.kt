package data.mappers.domain.dayConcentration

import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.dayConcentrations.model.RemoteDayConcentration
import domain.model.DayConcentration

fun DayConcentration.toRemote():RemoteDayConcentration {

    val materialStorage = MaterialMySQLStorage(DatabaseConnectionData())

    val materials = materialStorage.getAll()

    return RemoteDayConcentration(
        id = id,
        materialId = materials.find { it.name == materialName }!!.id,
        value = value,
        year = year,
        carcinogenicRisk = carcinogenicRisk,
        nonCarcinogenicRisk = nonCarcinogenicRisk,
        organ = organ,
        carcinogenicRiskLevel = carcinogenicRiskLevel,
        nonCarcinogenicRiskLevel = nonCarcinogenicRiskLevel
    )
}