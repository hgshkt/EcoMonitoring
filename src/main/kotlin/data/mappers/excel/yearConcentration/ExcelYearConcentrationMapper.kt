package data.mappers.excel.yearConcentration

import data.storage.excel.yearConcentrations.excel.ExcelYearConcentration
import data.storage.remote.yearConcentrations.model.RemoteYearConcentration
import domain.model.YearConcentration

fun ExcelYearConcentration.toDomain() = YearConcentration(
    id = id,
    materialId = materialId,
    value = value,
    year = year,
    carcinogenicRisk = 0.0,
    nonCarcinogenicRisk = 0.0,
    organ = "-"
)

fun ExcelYearConcentration.toRemote() = RemoteYearConcentration(
    id = id,
    materialId = materialId,
    value = value,
    year = year,
    organ = "-"
)