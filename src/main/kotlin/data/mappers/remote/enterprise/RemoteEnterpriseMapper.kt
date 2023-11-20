package data.mappers.remote.enterprise

import data.storage.excel.enterprises.model.ExcelEnterprise
import data.storage.remote.enterprises.model.RemoteEnterprise
import domain.model.Enterprise

fun RemoteEnterprise.toExcel() = ExcelEnterprise(
    id = id,
    name = name,
    activity = activity,
    belonging = belonging,
    location = location
)

fun RemoteEnterprise.toDomain() = Enterprise(
    id = id,
    name = name,
    activity = activity,
    belonging = belonging,
    location = location
)