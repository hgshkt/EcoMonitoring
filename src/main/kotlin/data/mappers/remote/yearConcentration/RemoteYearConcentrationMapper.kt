package data.mappers.remote.yearConcentration

import data.storage.excel.yearConcentrations.excel.ExcelYearConcentration
import data.storage.remote.yearConcentrations.model.RemoteYearConcentration
import domain.model.YearConcentration

fun RemoteYearConcentration.toDomain() = YearConcentration(
    id = id,
    materialId = materialId,
    value = value,
    year = year
)

fun RemoteYearConcentration.toExcel() = ExcelYearConcentration(
    id = id,
    materialId = materialId,
    value = value,
    year = year
)