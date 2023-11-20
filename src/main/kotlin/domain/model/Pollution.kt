package domain.model

data class Pollution(
    val enterpriseName: String,
    val materialName: String,
    val year: Int,
    val materialAmount: Double
)