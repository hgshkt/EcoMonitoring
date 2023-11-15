package data.storage.pollutions

import data.storage.DatabaseConnectionData
import data.storage.materials.model.RemoteStorageMaterial
import data.storage.pollutions.model.RemoteStoragePollution
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class PollutionsMySQLStorage(
    private val connectionData: DatabaseConnectionData
) : PollutionsRemoteStorage {

    private val getAllQuery = "SELECT * FROM pollutions"

    private val columnIdName = "id"
    private val columnEnterpriseIdName = "enterprise_id"
    private val columnMaterialIdName = "material_id"
    private val columnYearName = "year"
    private val columnAmountName = "material_amount"

    override fun getData(): List<RemoteStoragePollution> {
        val pollutions = mutableListOf<RemoteStoragePollution>()

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

                val pollution = RemoteStoragePollution(
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
}