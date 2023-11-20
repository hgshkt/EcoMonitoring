package data.mappers.excel.enterprise

import data.storage.excel.enterprises.model.ExcelEnterprise
import data.storage.remote.enterprises.model.RemoteEnterprise
import domain.model.Enterprise

fun ExcelEnterprise.toDomain() = Enterprise(
    id = id,
    name = name,
    activity = activity,
    belonging = belonging,
    location = location
)

fun ExcelEnterprise.toRemote() = RemoteEnterprise(
    id = id,
    name = name,
    activity = activity,
    belonging = belonging,
    location = location
)