package data.mappers.remote.material

import data.storage.excel.materials.model.ExcelMaterial
import data.storage.remote.materials.model.RemoteMaterial
import domain.model.Material

fun RemoteMaterial.toExcel() = ExcelMaterial(
    id = id,
    name = name,
    gdk = gdk,
    dangerClass = dangerClass,
    RfC = RfC
)

fun RemoteMaterial.toDomain() = Material(
    id = id,
    name = name,
    gdk = gdk,
    dangerClass = dangerClass,
    RfC = RfC,
    organ = organ
)