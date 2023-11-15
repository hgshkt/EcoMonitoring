package data.storage.remote.pollutions.model

data class RemotePollution(
    val id: Int,
    val enterpriseId: Int,
    val materialId: Int,
    val year: Int,
    val materialAmount: Double
)
