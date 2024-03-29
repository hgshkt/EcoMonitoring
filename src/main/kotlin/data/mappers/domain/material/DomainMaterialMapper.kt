package data.mappers.domain.material

import data.storage.excel.materials.model.ExcelMaterial
import data.storage.remote.materials.model.RemoteMaterial
import domain.model.Material

fun Material.toExcel() = ExcelMaterial(
    id = id,
    name = name,
    gdk = gdk,
    dangerClass = dangerClass,
    RfC = RfC,
    organ = organ,
    gdv = gdv,
    massEmissions = massEmissions
)

fun Material.toRemote() = RemoteMaterial(
    id = id,
    name = name,
    gdk = gdk,
    dangerClass = dangerClass,
    RfC = RfC,
    organ = organ,
    gdv = gdv,
    massEmissions = massEmissions,
    Ai = Ai,
    Kzi = Kzi
)