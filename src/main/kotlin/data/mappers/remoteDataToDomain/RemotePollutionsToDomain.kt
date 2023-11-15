package data.mappers.remoteDataToDomain

import data.storage.remote.pollutions.model.RemotePollution

fun RemotePollution.toDomain(
    mapper: MapperRemoteToDomainPollution
) = mapper.toDomain(this)