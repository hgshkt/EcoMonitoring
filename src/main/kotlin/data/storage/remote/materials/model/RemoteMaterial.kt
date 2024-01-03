package data.storage.remote.materials.model

data class RemoteMaterial(
    val id: Int,
    val name: String,
    val gdk: Double,
    val dangerClass: Int,
    val RfC: Double,
    val organ: String,
    val gdv: Int,
    val massEmissions: Int,
    val Ai: Double,
    val Kzi: Double
)
