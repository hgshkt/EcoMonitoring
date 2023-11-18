package data.storage.remote.pollutions

import data.storage.DatabaseConnectionData
import data.storage.remote.enterprises.model.RemoteEnterprise
import data.storage.remote.pollutions.model.RemotePollution
import java.sql.*

class PollutionsMySQLStorage(
    private val connectionData: DatabaseConnectionData
) : PollutionsRemoteStorage {

    private val tableName = "pollutions"

    private val getAllQuery = "SELECT * FROM $tableName"
    private val addQuery = "INSERT INTO $tableName VALUES(?, ?, ?, ?, ?)"

    private val columnIdName = "id"
    private val columnEnterpriseIdName = "enterprise_id"
    private val columnMaterialIdName = "material_id"
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
                val id = resultSet.getInt(columnIdName)
                val enterpriseId = resultSet.getInt(columnEnterpriseIdName)
                val materialId = resultSet.getInt(columnMaterialIdName)
                val year = resultSet.getInt(columnYearName)
                val materialAmount = resultSet.getDouble(columnAmountName)

                val pollution = RemotePollution(
                    id = id,
                    enterpriseId = enterpriseId,
                    materialId = materialId,
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
        val connection: Connection = DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        )

        for (pollution in pollutions) {
            val preparedStatement: PreparedStatement =
                connection.prepareStatement(addQuery)

            preparedStatement.setInt(1, pollution.id)
            preparedStatement.setInt(2, pollution.enterpriseId)
            preparedStatement.setInt(3, pollution.materialId)
            preparedStatement.setInt(4, pollution.year)
            preparedStatement.setDouble(5, pollution.materialAmount)

            preparedStatement.executeQuery(addQuery)
        }
    }
}