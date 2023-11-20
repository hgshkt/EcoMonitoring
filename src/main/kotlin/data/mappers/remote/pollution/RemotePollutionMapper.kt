package data.mappers.remote.pollution

import data.storage.excel.pollutions.model.ExcelPollution
import data.storage.remote.pollutions.model.RemotePollution
import domain.model.Pollution

fun RemotePollution.toExcel() = ExcelPollution(
    enterpriseName = enterpriseName,
    materialName = materialName,
    year = year,
    materialAmount = materialAmount
)

fun RemotePollution.toDomain() = Pollution(
    enterpriseName = enterpriseName,
    materialName = materialName,
    year = year,
    materialAmount = materialAmount
)