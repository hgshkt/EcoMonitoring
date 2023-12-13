package data.storage.remote.pollutions

import data.storage.DatabaseConnectionData
import data.storage.remote.pollutions.model.RemotePollution
import domain.model.Pollution
import java.sql.*

class PollutionsMySQLStorage(
    private val connectionData: DatabaseConnectionData
) : PollutionsRemoteStorage {

    private val tableName = "pollutions"

    private val columnEnterpriseIdName = "enterprise_id"
    private val columnMaterialIdName = "material_id"
    private val columnYearName = "year"
    private val columnAmountName = "material_amount"

    private val getAllQuery = "SELECT * FROM $tableName"
    private val insertQuery = "INSERT INTO $tableName VALUES(?, ?, ?, ?)"
    private val deleteQuery = "DELETE FROM $tableName WHERE $columnEnterpriseIdName = ? AND " +
            "$columnMaterialIdName = ?"
    private val deleteAllQuery = "DELETE FROM $tableName"

    override fun getAll(): List<RemotePollution> {
        val pollutions = mutableListOf<RemotePollution>()

        try {
            val connection: Connection = DriverManager.getConnection(
                connectionData.url,
                connectionData.user,
                connectionData.password
            )

            val statement = connection.createStatement()
            val resultSet: ResultSet = statement.executeQuery(getAllQuery)

            while (resultSet.next()) {
                val pollution = RemotePollution(
                    enterpriseId = resultSet.getInt(columnEnterpriseIdName),
                    materialId = resultSet.getInt(columnMaterialIdName),
                    year = resultSet.getInt(columnYearName),
                    materialAmount = resultSet.getDouble(columnAmountName)
                )
                pollutions.add(pollution)
            }

            resultSet.close()
            statement.close()
            connection.close()

        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return pollutions
    }

    override fun add(pollution: RemotePollution) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->
            connection.prepareStatement(insertQuery).use { preparedStatement ->

                preparedStatement.setInt(1, pollution.enterpriseId)
                preparedStatement.setInt(2, pollution.materialId)
                preparedStatement.setInt(3, pollution.year)
                preparedStatement.setDouble(4, pollution.materialAmount)

                preparedStatement.executeUpdate()
            }
        }
    }


    override fun delete(pollution: RemotePollution) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->
            connection.prepareStatement(deleteQuery).use { prepareStatement ->

                prepareStatement.setInt(1, pollution.enterpriseId)
                prepareStatement.setInt(2, pollution.materialId)

                prepareStatement.executeUpdate()
            }
        }
    }

    override fun deleteAll() {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->
            connection.createStatement().use { statement ->
                statement.executeUpdate(deleteAllQuery)
            }
        }
    }
}