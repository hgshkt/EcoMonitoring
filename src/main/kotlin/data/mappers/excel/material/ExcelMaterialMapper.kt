package data.mappers.excel.material

import data.storage.excel.materials.model.ExcelMaterial
import data.storage.remote.materials.model.RemoteMaterial
import domain.model.Material

fun ExcelMaterial.toDomain() = Material(
    id = id,
    name = name,
    gdk = gdk,
    dangerClass = dangerClass,
    RfC = RfC,
    organ = organ,
    gdv = gdv,
    massEmissions = massEmissions
)

fun ExcelMaterial.toRemote() = RemoteMaterial(
    id = id,
    name = name,
    gdk = gdk,
    dangerClass = dangerClass,
    RfC = RfC,
    organ = organ,
    gdv = gdv,
    massEmissions = massEmissions
)