package data.mappers.domain.taxRate

import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.taxRate.model.RemoteTaxRate
import domain.model.TaxRate

fun TaxRate.toRemote(): RemoteTaxRate {

    val materialStorage = MaterialMySQLStorage(DatabaseConnectionData())

    val materialId = materialStorage.getByName(materialName)!!.id

    return RemoteTaxRate(
        id = id,
        materialId = materialId,
        value = value
    )
}