package data.storage.remote.enterprises.model

data class RemoteEnterprise(
    val id: Int,
    val name: String,
    val activity: String,
    val belonging: String = "-*-",
    val location: String
)
