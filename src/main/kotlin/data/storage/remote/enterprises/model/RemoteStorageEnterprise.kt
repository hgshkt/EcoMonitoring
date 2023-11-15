package data.storage.remote.enterprises.model

data class RemoteStorageEnterprise(
    val id: Int,
    val name: String,
    val activity: String,
    val belonging: String = "-*-",
    val location: String
)
