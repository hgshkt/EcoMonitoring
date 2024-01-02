package data.storage.remote.damageData

import data.storage.DatabaseConnectionData
import data.storage.remote.damageData.model.RemoteDamageData
import java.sql.DriverManager

class DamageDataRemoteStorageImpl(
    private val connectionData: DatabaseConnectionData
) : DamageDataRemoteStorage {

    private val tableName = "damage_data"

    private val columnPopulationName = "population"
    private val columnPName = "P"
    private val columnKfName = "kf"
    private val columnKnasName = "knas"
    private val columnKtName = "kt"

    private val getQuery = "SELECT * FROM $tableName LIMIT 1"
    private val deleteQuery = "DELETE FROM $tableName"
    private val putQuery = "INSERT INTO $tableName VALUES (?, ?, ?, ?, ?)"

    override fun put(data: RemoteDamageData) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->

            val deleteStatement = connection.prepareStatement(deleteQuery)
            deleteStatement.executeUpdate()

            connection.prepareStatement(putQuery).use { statement ->
                statement.setInt(1, data.population)
                statement.setInt(2, data.P)
                statement.setDouble(3, data.kf)
                statement.setDouble(4, data.knas)
                statement.setDouble(5, data.kt)

                statement.executeUpdate()
            }
        }
    }

    override fun get(): RemoteDamageData? {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).createStatement().use { statement ->
            val resultSet = statement.executeQuery(getQuery)

            if (resultSet.next())
                return RemoteDamageData(
                    population = resultSet.getInt(columnPopulationName),
                    P = resultSet.getInt(columnPName),
                    kf = resultSet.getDouble(columnKfName),
                    knas = resultSet.getDouble(columnKnasName),
                    kt = resultSet.getDouble(columnKtName),
                )

            else return null
        }
    }

    override fun delete() {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).prepareStatement(deleteQuery).executeUpdate()
    }
}