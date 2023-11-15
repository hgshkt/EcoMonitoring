package domain.model

data class Pollution(
    val id: Int,
    val enterpriseName: String,
    val materialName: String,
    val year: Int,
    val materialAmount: Double
)