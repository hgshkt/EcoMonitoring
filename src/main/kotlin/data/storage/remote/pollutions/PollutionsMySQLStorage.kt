package data.storage.remote.pollutions

import data.storage.DatabaseConnectionData
import data.storage.remote.pollutions.model.RemotePollution
import java.sql.*

class PollutionsMySQLStorage(
    private val connectionData: DatabaseConnectionData
) : PollutionsRemoteStorage {

    private val tableName = "pollutions"

    private val getAllQuery = "SELECT * FROM $tableName"
    private val insertQuery = "INSERT INTO $tableName VALUES(?, ?, ?, ?)"

    private val columnEnterpriseIdName = "enterprise_name"
    private val columnMaterialIdName = "material_name"
    private val columnYearName = "year"
    private val columnAmountName = "material_amount"

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
                val enterpriseName = resultSet.getString(columnEnterpriseIdName)
                val materialName = resultSet.getString(columnMaterialIdName)
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
}