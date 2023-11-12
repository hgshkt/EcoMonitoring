package domain.model

data class Pollution(
    val id: Int,
    val enterpriseId: Int,
    val materialId: Int,
    val year: Int,
    val materialAmount: Double
)