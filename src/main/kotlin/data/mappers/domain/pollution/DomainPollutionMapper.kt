package data.mappers.domain.pollution

import data.storage.excel.pollutions.model.ExcelPollution
import data.storage.remote.pollutions.model.RemotePollution
import domain.model.Pollution

fun Pollution.toExcel() = ExcelPollution(
    enterpriseName = enterpriseName,
    materialName = materialName,
    year = year,
    materialAmount = materialAmount
)

fun Pollution.toRemote() = RemotePollution(
    enterpriseName = enterpriseName,
    materialName = materialName,
    year = year,
    materialAmount = materialAmount
)