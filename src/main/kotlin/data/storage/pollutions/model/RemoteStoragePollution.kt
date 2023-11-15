package data.storage.pollutions.model

data class RemoteStoragePollution(
    val id: Int,
    val enterpriseId: Int,
    val materialId: Int,
    val year: Int,
    val materialAmount: Double
)
