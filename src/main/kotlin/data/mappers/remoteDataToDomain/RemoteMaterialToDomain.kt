package data.mappers.remoteDataToDomain

import data.storage.remote.materials.model.RemoteMaterial
import domain.model.Material

fun RemoteMaterial.toDomain() = Material(
    id = id,
    name = name,
    gdk = gdk,
    dangerClass = dangerClass
)