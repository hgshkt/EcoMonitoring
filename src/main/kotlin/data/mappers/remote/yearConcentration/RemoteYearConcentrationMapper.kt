package data.mappers.remote.yearConcentration

import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.yearConcentrations.model.RemoteYearConcentration
import domain.model.YearConcentration

fun RemoteYearConcentration.toDomain(): YearConcentration {

    val materialStorage = MaterialMySQLStorage(DatabaseConnectionData())

    val materials = materialStorage.getAll()

    return YearConcentration(
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