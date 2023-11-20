package data.storage.remote.pollutions.model

data class RemotePollution(
    val enterpriseName: String,
    val materialName: String,
    val year: Int,
    val materialAmount: Double
)
