package data.mappers.domain.enterprise

import data.storage.excel.enterprises.model.ExcelEnterprise
import data.storage.remote.enterprises.model.RemoteEnterprise
import domain.model.Enterprise

fun Enterprise.toExcel() = ExcelEnterprise(
    id = id,
    name = name,
    activity = activity,
    belonging = belonging,
    location = location
)

fun Enterprise.toRemote() = RemoteEnterprise(
    id = id,
    name = name,
    activity = activity,
    belonging = belonging,
    location = location
)