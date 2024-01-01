package data.mappers.remote.yearConcentration

import data.repository.materials.remote.MaterialMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.yearConcentration.model.RemoteYearConcentration
import domain.model.YearConcentration

fun RemoteYearConcentration.toDomain(): YearConcentration {

    val materialRepository = MaterialMySQLRepository(
        storage = MaterialMySQLStorage(
            connectionData = DatabaseConnectionData()
        )
    )

    return YearConcentration(
        id = id,
        materialName = materialRepository.getData().materials.find { it.id == materialId}!!.name,
        year = year,
        value = value
    )
}