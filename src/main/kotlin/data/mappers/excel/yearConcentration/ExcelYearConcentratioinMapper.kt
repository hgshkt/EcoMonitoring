package data.mappers.excel.yearConcentration

import data.repository.materials.remote.MaterialMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.excel.yearConcentration.model.ExcelYearConcentration
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.yearConcentration.model.RemoteYearConcentration
import domain.model.YearConcentration

fun ExcelYearConcentration.toDomain(): YearConcentration {

    return YearConcentration(
        id = -1,
        materialName = materialName,
        year = year,
        value = value
    )
}

fun ExcelYearConcentration.toRemote(): RemoteYearConcentration {
    val materialRepository = MaterialMySQLRepository(
        storage = MaterialMySQLStorage(
            connectionData = DatabaseConnectionData()
        )
    )

    return RemoteYearConcentration(
        id = -1,
        materialId = materialRepository.getByName(materialName).id,
        year = year,
        value = value
    )
}