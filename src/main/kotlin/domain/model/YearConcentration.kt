package domain.model

data class YearConcentration(
    val id: Int,
    val materialId: Int,
    val value: Double,
    val year: Int,
    val carcinogenicRisk: Double,
    val nonCarcinogenicRisk: Double
)
