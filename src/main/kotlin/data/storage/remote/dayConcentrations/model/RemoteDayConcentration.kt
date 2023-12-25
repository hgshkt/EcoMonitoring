package data.storage.remote.dayConcentrations.model

data class RemoteDayConcentration(
    val id: Int,
    val materialId: Int,
    val value: Double,
    val year: Int,
    val carcinogenicRisk: Double = 0.0,
    val nonCarcinogenicRisk: Double = 0.0,
    var organ: String = "-"
)
