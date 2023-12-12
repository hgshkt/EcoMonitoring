package data.mappers.excel.yearConcentration

import data.storage.DatabaseConnectionData
import data.storage.excel.yearConcentrations.excel.ExcelYearConcentration
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.yearConcentrations.model.RemoteYearConcentration
import domain.model.YearConcentration

fun ExcelYearConcentration.toDomain(): YearConcentration {

    val materialStorage = MaterialMySQLStorage(DatabaseConnectionData())

    val materials = materialStorage.getAll()

    return YearConcentration(
        id = id,
        materialName = materials.find { it.id == materialId }!!.name,
        value = value,
        year = year,
    )
}

fun ExcelYearConcentration.toRemote() = RemoteYearConcentration(
    id = id,
    materialId = materialId,
    value = value,
    year = year,
    organ = "-"
)