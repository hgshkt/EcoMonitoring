package domain.model

data class YearConcentration(
    var id: Int = -1,
    val materialId: Int,
    val value: Double,
    val year: Int,
    var carcinogenicRisk: Double = 0.0,
    var nonCarcinogenicRisk: Double = 0.0,
    var organ: String = "-",
    var carcinogenicRiskLevel: String = "-",
    var nonCarcinogenicRiskLevel: String = "-"
)
