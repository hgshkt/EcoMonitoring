package data.mappers.remote.pollution

import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.pollutions.model.RemotePollution
import domain.model.Pollution

fun RemotePollution.toDomain(): Pollution {

    val enterpriseStorage = EnterprisesMySQLStorage(DatabaseConnectionData())
    val materialStorage = MaterialMySQLStorage(DatabaseConnectionData())

    val enterprises = enterpriseStorage.getAll()
    val materials = materialStorage.getAll()

    return Pollution(
        enterpriseName = enterprises.find {it.id == enterpriseId}?.name ?: "Not found",
        materialName = materials.find {it.id == materialId}?.name ?: "Not found",
        year = year,
        materialAmount = materialAmount,
        concentration = concentration,
        carcinogenicRisk = carcinogenicRisk,
        carcinogenicRiskLevel = carcinogenicRiskLevel,
        nonCarcinogenicRisk = nonCarcinogenicRisk,
        nonCarcinogenicRiskLevel = nonCarcinogenicRiskLevel,
        damage = damage
    )
}