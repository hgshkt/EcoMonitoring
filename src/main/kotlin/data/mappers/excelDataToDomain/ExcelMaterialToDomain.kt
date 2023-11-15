package data.mappers.excelDataToDomain

import data.storage.excel.materials.model.ExcelMaterial
import domain.model.Material

fun ExcelMaterial.toDomain() = Material(
    id = id,
    name = name,
    gdk = gdk,
    dangerClass = dangerClass
)