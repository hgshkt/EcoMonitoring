package data.mappers.domain.yearConcentration

import data.repository.materials.remote.MaterialMySQLRepository
import data.storage.DatabaseConnectionData
import data.storage.remote.materials.MaterialMySQLStorage
import data.storage.remote.yearConcentration.model.RemoteYearConcentration
import domain.model.YearConcentration

fun YearConcentration.toRemote(): RemoteYearConcentration {

    val materialRepository = MaterialMySQLRepository(
        storage = MaterialMySQLStorage(
            connectionData = DatabaseConnectionData()
        )
    )

    return RemoteYearConcentration(
        id = id,
        materialId = materialRepository.getByName(materialName).id,
        year = year,
        value = value
    )
}