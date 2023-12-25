package domain.model

data class DayConcentration(
    var id: Int = -1,
    val materialName: String,
    val value: Double,
    val year: Int,
    var carcinogenicRisk: Double = 0.0,
    var nonCarcinogenicRisk: Double = 0.0,
    var organ: String = "-"
)
