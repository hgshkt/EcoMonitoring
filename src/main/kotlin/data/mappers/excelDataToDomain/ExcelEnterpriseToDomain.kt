package data.mappers.excelDataToDomain

import data.storage.excel.enterprises.model.ExcelEnterprise
import domain.model.Enterprise

fun ExcelEnterprise.toDomain() = Enterprise(
    id = id,
    name = name,
    activity = activity,
    belonging = belonging,
    location = location
)