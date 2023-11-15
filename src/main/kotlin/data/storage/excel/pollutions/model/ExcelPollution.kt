package data.storage.excel.pollutions.model

data class ExcelPollution(
    val id: Int,
    val enterpriseId: Int,
    val materialId: Int,
    val year: Int,
    val materialAmount: Double
)
