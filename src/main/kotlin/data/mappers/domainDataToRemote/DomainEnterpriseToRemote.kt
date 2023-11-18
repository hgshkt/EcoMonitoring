package data.mappers.domainDataToRemote

import data.storage.remote.enterprises.model.RemoteEnterprise
import domain.model.Enterprise

fun Enterprise.toRemote() = RemoteEnterprise(
    id = id,
    name = name,
    activity = activity,
    belonging = belonging,
    location = location
)