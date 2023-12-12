package data.mappers.domain.yearConcentration

import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.yearConcentrations.model.RemoteYearConcentration
import domain.model.YearConcentration

fun YearConcentration.toRemote():RemoteYearConcentration {

    val materialStorage = MaterialMySQLStorage(DatabaseConnectionData())

    val materials = materialStorage.getAll()

    return RemoteYearConcentration(
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