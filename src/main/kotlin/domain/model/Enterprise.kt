package domain.model

data class Enterprise(
    val id: Int,
    val name: String,
    val activity: String,
    val belonging: String = "-*-",
    val location: String
)
