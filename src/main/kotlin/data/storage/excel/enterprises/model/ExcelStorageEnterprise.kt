package data.storage.excel.enterprises.model

data class ExcelStorageEnterprise(
    val id: Int,
    val name: String,
    val activity: String,
    val belonging: String = "-*-",
    val location: String
)
