package data.mappers.domain.yearConcentration

import data.storage.excel.yearConcentrations.excel.ExcelYearConcentration
import data.storage.remote.yearConcentrations.model.RemoteYearConcentration
import domain.model.YearConcentration

fun YearConcentration.toExcel() = ExcelYearConcentration(
    id = id,
    materialId = materialId,
    value = value,
    year = year
)

fun YearConcentration.toRemote() = RemoteYearConcentration(
    id = id,
    materialId = materialId,
    value = value,
    year = year,
    carcinogenicRisk = carcinogenicRisk,
    nonCarcinogenicRisk = nonCarcinogenicRisk,
    organ = organ
)