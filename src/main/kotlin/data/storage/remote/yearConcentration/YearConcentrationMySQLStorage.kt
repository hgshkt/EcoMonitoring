package data.storage.remote.yearConcentration

import data.storage.DatabaseConnectionData
import data.storage.remote.yearConcentration.model.RemoteYearConcentration
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class YearConcentrationMySQLStorage(
    private val connectionData: DatabaseConnectionData
) : YearConcentrationRemoteStorage {

    private val tableName = "year_concentration"

    private val columnIdName = "id"
    private val columnMaterialIdName = "material_id"
    private val columnYearName = "year"
    private val columnValueName = "value"

    private val getAllQuery = "SELECT * FROM $tableName"

    private val insertQuery =
        "INSERT INTO $tableName($columnMaterialIdName, $columnYearName, $columnValueName) VALUES(?, ?, ?)"

    private val updateQuery = "UPDATE $tableName SET (" +
            "$columnMaterialIdName = ?, $columnYearName = ?, $columnValueName = ?)" +
            "WHERE $columnIdName = ?"

    private val deleteQuery = "DELETE FROM $tableName WHERE $columnIdName = ?"
    private val deleteAllQuery = "DELETE FROM $tableName"

    override fun getAll(): List<RemoteYearConcentration> {
        val concentrations = mutableListOf<RemoteYearConcentration>()

        try {
            val connection: Connection = DriverManager.getConnection(
                connectionData.url,
                connectionData.user,
                connectionData.password
            )

            val statement = connection.createStatement()
            val resultSet: ResultSet = statement.executeQuery(getAllQuery)

            while (resultSet.next()) {
                concentrations.add(
                    element = RemoteYearConcentration(
                        id = resultSet.getInt(columnIdName),
                        materialId = resultSet.getInt(columnMaterialIdName),
                        year = resultSet.getInt(columnYearName),
                        value = resultSet.getDouble(columnValueName)
                    )
                )
            }

            resultSet.close()
            statement.close()
            connection.close()

        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return concentrations
    }

    override fun add(concentration: RemoteYearConcentration) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->

            connection.prepareStatement(insertQuery).use { preparedStatement ->

                preparedStatement.setInt(1, concentration.materialId)
                preparedStatement.setInt(2, concentration.year)
                preparedStatement.setDouble(3, concentration.value)

                preparedStatement.executeUpdate()
            }
        }
    }

    override fun update(concentration: RemoteYearConcentration) {
        DriverManager.getConnection(
            connectionData.url,
            connectionData.user,
            connectionData.password
        ).use { connection ->
            connection.prepareStatement(updateQuery).use { prepareStatement ->
                prepareStatement.setInt(1, concentration.materialId)
                prepareStatement.setInt(2, concentration.year)
                prepareStatement.setDouble(3, concentration.value)

                prepareStatement.setInt(4, concentration.id)

                prepareStatement.executeUpdate()
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