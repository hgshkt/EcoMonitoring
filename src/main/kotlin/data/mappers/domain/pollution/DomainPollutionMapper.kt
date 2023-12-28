package data.mappers.domain.pollution

import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.EnterprisesMySQLStorage
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.pollutions.model.RemotePollution
import domain.model.Pollution


fun Pollution.toRemote(): RemotePollution {

    val enterpriseStorage = EnterprisesMySQLStorage(DatabaseConnectionData())
    val materialStorage = MaterialMySQLStorage(DatabaseConnectionData())

    val enterprises = enterpriseStorage.getAll()
    val materials = materialStorage.getAll()

    return RemotePollution(
        enterpriseId = enterprises.find {it.name == enterpriseName}!!.id,
        materialId = materials.find {it.name == materialName}!!.id,
        year = year,
        materialAmount = materialAmount,
        concentration = concentration,
        carcinogenicRisk = carcinogenicRisk,
        carcinogenicRiskLevel = carcinogenicRiskLevel,
        nonCarcinogenicRisk = nonCarcinogenicRisk,
        nonCarcinogenicRiskLevel = nonCarcinogenicRiskLevel
    )
}