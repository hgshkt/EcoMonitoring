package domain.model

data class Pollution(
    val enterpriseName: String,
    val materialName: String,
    val year: Int,
    val materialAmount: Double,
    val concentration: Double,
    var carcinogenicRisk: Double = -1.0,
    var carcinogenicRiskLevel: String = "-",
    var nonCarcinogenicRisk: Double = -1.0,
    var nonCarcinogenicRiskLevel: String = "-",
    var damage: Double = -1.0
)