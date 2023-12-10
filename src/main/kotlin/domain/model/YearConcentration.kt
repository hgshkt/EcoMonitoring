package domain.model

data class YearConcentration(
    val id: Int,
    val materialId: Int,
    val value: Double,
    val year: Int,
    var carcinogenicRisk: Double = 0.0,
    var nonCarcinogenicRisk: Double = 0.0
)
