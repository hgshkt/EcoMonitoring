package data.mappers.domainDataToRemote

import data.storage.remote.materials.model.RemoteMaterial
import domain.model.Material

fun Material.toRemote() = RemoteMaterial(
    id = id,
    name = name,
    gdk = gdk,
    dangerClass = dangerClass
)