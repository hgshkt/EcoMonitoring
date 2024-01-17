package data.storage.remote.taxRate

import data.storage.DatabaseConnectionData
import data.storage.remote.taxRate.model.RemoteTaxRate
import java.sql.DriverManager
import java.sql.ResultSet

class TaxRateMySQLStorage(
    private val connectionData: DatabaseConnectionData
) : TaxRateRemoteStorage {
    private val tableName = "tax_rate_atmosphere"

    private val columnIdName = "id"
    private val columnMaterialIdName = "material_id"
    private val columnValueName = "value"

    private val getAllQuery = "SELECT * FROM $tableName"

    private val insertQuery =
        "INSERT INTO $tableName($columnMaterialIdName, $columnValueName) VALUES(?, ?)"

    private val deleteQuery = "DELETE FROM $tableName WHERE $columnIdName = ?"
    private val deleteAllQuery = "DELETE FROM $tableName"

    override fun getAll(): List<RemoteTaxRate> {
        val taxRates = mutableListOf<RemoteTaxRate>()

        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).createStatement().use { statement ->
            val resultSet: ResultSet = statement.executeQuery(getAllQuery)

            while (resultSet.next()) {
                taxRates.add(
                    RemoteTaxRate(
                        id = resultSet.getInt(columnIdName),
                        materialId = resultSet.getInt(columnMaterialIdName),
                        value = resultSet.getDouble(columnValueName)
                    )
                )
            }
        }
        return taxRates
    }

    override fun add(taxRate: RemoteTaxRate) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->

            connection.prepareStatement(insertQuery).use { preparedStatement ->

                preparedStatement.setInt(1, taxRate.materialId)
                preparedStatement.setDouble(2, taxRate.value)

                preparedStatement.executeUpdate()
            }
        }
    }

    override fun deleteById(id: Int) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->
            connection.prepareStatement(deleteQuery).use { prepareStatement ->
                prepareStatement.setString(1, id.toString())
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