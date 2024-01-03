package domain.model

data class Material(
    val id: Int,
    val name: String,
    val gdk: Double,
    val dangerClass: Int,
    val RfC: Double,
    val organ: String,
    val gdv: Int,
    val massEmissions: Int,
    var Ai: Double,
    var Kzi: Double
)