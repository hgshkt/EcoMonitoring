package data.storage.remote.pollutions.model

data class RemotePollution(
    val enterpriseId: Int,
    val materialId: Int,
    val year: Int,
    val materialAmount: Double,
    val concentration: Double,
    var carcinogenicRisk: Double,
    var carcinogenicRiskLevel: String,
    var nonCarcinogenicRisk: Double,
    var nonCarcinogenicRiskLevel: String,
    var damage: Double = -1.0
)