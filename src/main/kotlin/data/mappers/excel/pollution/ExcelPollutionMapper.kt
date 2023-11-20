package data.mappers.excel.pollution

import data.storage.excel.pollutions.model.ExcelPollution
import data.storage.remote.pollutions.model.RemotePollution
import domain.model.Pollution

fun ExcelPollution.toDomain() = Pollution(
    enterpriseName = enterpriseName,
    materialName = materialName,
    year = year,
    materialAmount = materialAmount
)

fun ExcelPollution.toRemote() = RemotePollution(
    enterpriseName = enterpriseName,
    materialName = materialName,
    year = year,
    materialAmount = materialAmount
)