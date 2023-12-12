package data.storage.remote.yearConcentrations.model

data class RemoteYearConcentration(
    val id: Int,
    val materialId: Int,
    val value: Double,
    val year: Int,
    val carcinogenicRisk: Double = 0.0,
    val nonCarcinogenicRisk: Double = 0.0,
    var organ: String = "-",
    var carcinogenicRiskLevel: String = "-",
    var nonCarcinogenicRiskLevel: String = "-"
)
