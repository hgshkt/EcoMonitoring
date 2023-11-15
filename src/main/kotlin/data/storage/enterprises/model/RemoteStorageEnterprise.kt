package data.storage.enterprises.model

data class RemoteStorageEnterprise(
    val id: Int,
    val name: String,
    val activity: String,
    val belonging: String = "-*-",
    val location: String
)
