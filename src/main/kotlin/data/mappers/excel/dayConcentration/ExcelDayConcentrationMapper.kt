package data.mappers.excel.dayConcentration

import data.storage.DatabaseConnectionData
import data.storage.excel.dayConcentrations.excel.ExcelDayConcentration
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.dayConcentrations.model.RemoteDayConcentration
import domain.model.DayConcentration

fun ExcelDayConcentration.toDomain(): DayConcentration {

    val materialStorage = MaterialMySQLStorage(DatabaseConnectionData())

    val materials = materialStorage.getAll()

    return DayConcentration(
        id = id,
        materialName = materials.find { it.id == materialId }!!.name,
        value = value,
        year = year,
    )
}

fun ExcelDayConcentration.toRemote() = RemoteDayConcentration(
    id = id,
    materialId = materialId,
    value = value,
    year = year,
    organ = "-"
)