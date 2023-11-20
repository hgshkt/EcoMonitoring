package data.storage.excel.pollutions.model

data class ExcelPollution(
    val enterpriseName: String,
    val materialName: String,
    val year: Int,
    val materialAmount: Double
)
