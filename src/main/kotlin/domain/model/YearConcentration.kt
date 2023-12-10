package domain.model

data class YearConcentration(
    val id: Int,
    val materialId: Int,
    val value: Double,
    val year: Int,
    var carcinogenicRisk: Double,
    var nonCarcinogenicRisk: Double
)
