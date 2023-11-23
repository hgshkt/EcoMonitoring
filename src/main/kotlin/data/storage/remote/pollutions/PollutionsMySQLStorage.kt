package data.storage.remote.pollutions

import data.storage.DatabaseConnectionData
import data.storage.remote.pollutions.model.RemotePollution
import domain.model.Pollution
import java.sql.*

class PollutionsMySQLStorage(
    private val connectionData: DatabaseConnectionData
) : PollutionsRemoteStorage {

    private val tableName = "pollutions"

    private val columnEnterpriseName = "enterprise_name"
    private val columnMaterialName = "material_name"
    private val columnYearName = "year"
    private val columnAmountName = "material_amount"

    private val getAllQuery = "SELECT * FROM $tableName"
    private val insertQuery = "INSERT INTO $tableName VALUES(?, ?, ?, ?)"
    private val deleteQuery = "DELETE FROM $tableName WHERE $columnAmountName = ? AND $columnMaterialName = ? AND $columnYearName = ? AND $columnAmountName = ?"

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
                val enterpriseName = resultSet.getString(columnEnterpriseName)
                val materialName = resultSet.getString(columnMaterialName)
                val year = resultSet.getInt(columnYearName)
                val materialAmount = resultSet.getDouble(columnAmountName)

                val pollution = RemotePollution(
                    enterpriseName = enterpriseName,
                    materialName = materialName,
                    year = year,
                    materialAmount = materialAmount
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

    override fun add(pollutions: List<RemotePollution>) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->
            connection.prepareStatement(insertQuery).use { preparedStatement ->

                for (pollution in pollutions) {

                    preparedStatement.setString(1, pollution.enterpriseName)
                    preparedStatement.setString(2, pollution.materialName)
                    preparedStatement.setInt(3, pollution.year)
                    preparedStatement.setDouble(4, pollution.materialAmount)

                    preparedStatement.executeUpdate()
                }
            }
        }
    }

    override fun delete(pollution: Pollution) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->
            connection.prepareStatement(deleteQuery).use { prepareStatement ->

                prepareStatement.setString(1, pollution.enterpriseName)
                prepareStatement.setString(2, pollution.materialName)
                prepareStatement.setInt(3, pollution.year)
                prepareStatement.setDouble(4, pollution.materialAmount)

                prepareStatement.executeUpdate()
            }
        }
    }
}