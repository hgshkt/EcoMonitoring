package data.storage.remote.yearConcentrations.model

data class RemoteYearConcentration(
    val id: Int,
    val materialId: Int,
    val value: Double,
    val year: Int,
    val carcinogenicRisk: Double,
    val nonCarcinogenicRisk: Double
)
