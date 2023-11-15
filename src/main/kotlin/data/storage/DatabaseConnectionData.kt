package data.storage

data class DatabaseConnectionData(
    val url: String = "jdbc:mysql://localhost:3306/eco_monitoring",
    val user: String = "root",
    val password: String = "password12345"
)
