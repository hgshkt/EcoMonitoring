package data.mappers.excel.taxRate

import data.storage.DatabaseConnectionData
import data.storage.excel.taxRate.model.ExcelTaxRate
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.taxRate.model.RemoteTaxRate
import domain.model.TaxRate

fun ExcelTaxRate.toDomain(): TaxRate {
    return TaxRate(
        id = -1,
        materialName = materialName,
        value = value
    )
}

fun ExcelTaxRate.toRemote(): RemoteTaxRate {
    val materialStorage = MaterialMySQLStorage(DatabaseConnectionData())

    val materialId = materialStorage.getByName(materialName)!!.id

    return RemoteTaxRate(
        id = -1,
        materialId = materialId,
        value = value
    )
}