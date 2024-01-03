package data.mappers.excel.pollution

import data.storage.excel.pollutions.model.ExcelPollution
import domain.model.Pollution

fun ExcelPollution.toDomain() = Pollution(
    enterpriseName = enterpriseName,
    materialName = materialName,
    year = year,
    materialAmount = materialAmount,
    concentration = concentration
)