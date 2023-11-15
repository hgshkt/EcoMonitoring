package data.mappers.remoteDataToDomain

import data.storage.remote.enterprises.model.RemoteEnterprise
import domain.model.Enterprise

fun RemoteEnterprise.toDomain() = Enterprise(
    id = id,
    name = name,
    activity = activity,
    belonging = belonging,
    location = location
)