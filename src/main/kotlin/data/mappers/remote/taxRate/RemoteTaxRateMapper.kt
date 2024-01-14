package data.mappers.remote.taxRate

import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.taxRate.model.RemoteTaxRate
import domain.model.TaxRate

fun RemoteTaxRate.toDomain(): TaxRate {
    val materialStorage = MaterialMySQLStorage(DatabaseConnectionData())

    val materialName = materialStorage.getById(materialId)!!.name

    return TaxRate(
        id = id,
        materialName = materialName,
        value = value
    )
}