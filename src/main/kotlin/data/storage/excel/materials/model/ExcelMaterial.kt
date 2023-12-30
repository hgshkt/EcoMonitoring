package data.storage.excel.materials.model

data class ExcelMaterial(
    val id: Int,
    val name: String,
    val gdk: Double,
    val dangerClass: Int,
    val RfC: Double,
    val organ: String,
    val gdv: Int,
    val massEmissions: Int
)
